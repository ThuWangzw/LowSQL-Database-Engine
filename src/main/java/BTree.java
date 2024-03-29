import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*

|-----db name length----|-----table name length-----|---2---|---2---|-------2-------|----Attribute name length--|--------2---------|--2--|
|        db name        |         table name        |root id|   M   |number of index|            ...            |empty block number|     |
|-----------------------|---------------------------|-------|-------|---------------|---------------------------|------------------|-----|

 */


public class BTree {
    String DB_name;
    String table_name;
    int root_id;
    TableSchema index_schema;
    ArrayList<Short> empty_blocks;
    IndexBuffer buffer;
    Boolean is_changed;
    int node_block_number;  //not include the first block
    int M;

    //create a new B tree
    public BTree(int m,String db_name,String t_name,TableSchema sa,IndexBuffer bf){
        M = m;
        DB_name = db_name;
        table_name = t_name;
        index_schema = sa;
        buffer = bf;
        empty_blocks = new ArrayList<>();
        is_changed = true;


        root_id = 1;
        BTreeNode root_node = new BTreeLeafNode(M,root_id,0,0,0,0,0,0,0,buffer,index_schema.getAttrubutes(),DB_name,table_name);
        buffer.addNewNode(root_node);

        node_block_number = 1;
    }

    //load a B tree from file
    public BTree(byte[] metadata,IndexBuffer bf,int blk_number){
        node_block_number = blk_number;
        buffer = bf;
        //name
        int i;
        for (i = 0; i < Util.DatabaseNameMaxLength; i++){
            if (metadata[i] == 0)
                break;
        }
        DB_name = new String(metadata,0,i);
        int limit = Util.DatabaseNameMaxLength + Util.TableNameMaxLength;
        for (i = Util.DatabaseNameMaxLength; i < limit; i++){
            if(metadata[i] == 0)
                break;
        }
        table_name = new String(metadata,Util.DatabaseNameMaxLength,i - Util.DatabaseNameMaxLength);

        byte[] bits2 = new byte[2];
        //root id
        System.arraycopy(metadata, Util.DatabaseNameMaxLength + Util.TableNameMaxLength,bits2,0,2);
        root_id = Util.byte2short(bits2);
        //M
        System.arraycopy(metadata, Util.DatabaseNameMaxLength + Util.TableNameMaxLength + 2,bits2,0,2);
        M = Util.byte2short(bits2);




        //index schema
        System.arraycopy(metadata,Util.DatabaseNameMaxLength + Util.TableNameMaxLength + 4,bits2,0,2);
        int index_number = (int) Util.byte2short(bits2);
        ArrayList<String> attr_names = new ArrayList<>();
        int j,start = Util.DatabaseNameMaxLength + Util.TableNameMaxLength + 6;
        String temp;
        for (i = 0; i < index_number; i++){
            for(j = 0; j < Util.AttributeNameMaxLength; j++){
                if (metadata[start + i * Util.AttributeNameMaxLength + j] == 0)
                    break;
            }
            temp = new String(metadata,start + i * Util.AttributeNameMaxLength, j);
            attr_names.add(temp);
        }
        index_schema = buffer.db.getOneTable(table_name).createIndexSchema(attr_names);

        //empty id
        empty_blocks = new ArrayList();
        start += Util.AttributeNameMaxLength * index_number;
        System.arraycopy(metadata,start,bits2,0,2);
        int empty_block_number = (int) Util.byte2short(bits2);
        start += 2;
        for (i = 0 ; i < empty_block_number; i++){
            System.arraycopy(metadata,start + i * 2,bits2,0,2);
            empty_blocks.add(Util.byte2short(bits2));
        }
        is_changed = false;
    }

    public byte[] toByte(){
        byte[] data = new byte[Util.DiskBlockSize];
        System.arraycopy(DB_name.getBytes(),0,data,0,DB_name.length());
        System.arraycopy(table_name.getBytes(),0,data,Util.DatabaseNameMaxLength,table_name.length());
        int start = Util.DatabaseNameMaxLength + Util.TableNameMaxLength;
        System.arraycopy(Util.short2byte((short)root_id),0,data,start,2);
        start += 2;
        System.arraycopy(Util.short2byte((short)M),0,data,start,2);
        start += 2;
        System.arraycopy(Util.short2byte((short)index_schema.getAttributeNumber()),0,data,start,2);
        start += 2;
        int len = index_schema.getAttributeNumber();
        TableAttribute[] temp = index_schema.getAttrubutes();
        for (int i = 0; i < len; i ++){
            String t = temp[i].getAttributeName();
            System.arraycopy(t.getBytes(),0,data,start + i * Util.AttributeNameMaxLength,t.length());
        }

        start += len * Util.AttributeNameMaxLength;
        len = empty_blocks.size();
        System.arraycopy(Util.short2byte((short)len),0,data,start,2);
        start += 2;
        for(int i = 0; i < len ; i++){
            System.arraycopy(Util.short2byte(empty_blocks.get(i)),0,data,start + i * 2,2);
        }
        return data;
    }

    public short getOneFreeBlockID(){
        short temp;
        if(empty_blocks.size() != 0){
            temp = empty_blocks.get(0);
            empty_blocks.remove(0);
        }else{
            node_block_number += 1;
            temp = (short)node_block_number;
        }
        is_changed = true;
        return temp;
    }

    public void addFreeBlockID(short id){
        empty_blocks.add(id);
        is_changed = true;
    }

    public String fileName(){
        return DB_name + "_" + table_name + "_" + index_schema.concatNames()+".bin";
    }

    public void insert(byte[] key,int page_id,int record_id){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).insert(key,page_id,record_id);
    }

    public void insert(Record rcd,int page_id,int record_id){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).insert(record2key(rcd),page_id,record_id);
    }

    //return [node_id index]
    public int[] query(Record rcd){
        return buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).query(record2key(rcd));
    }

    public int[] query(byte[] key){
        return buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).query(key);
    }

    public void delete(byte[] key){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).delete(key);
    }

    public void delete(byte[] key, DataPointer pt){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).delete(key,pt);
    }

    public void delete(Record rcd, DataPointer pt){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).delete(record2key(rcd),pt);
    }

    public void delete(Record rcd){
        buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes()).delete(record2key(rcd));
    }

    public byte[] record2key(Record record){
        BTreeNode node = buffer.getNode(root_id,DB_name,table_name,index_schema.getAttrubutes());
        if(node != null){
            return node.record2key(record);
        }
        throw new NullPointerException("The Index Tree is not initialized!");
    }

    public static void main(String[] args) {
        try {
            File file = new File(Util.IndexStorageDir);
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    if(!filelist[i].endsWith(".bin"))
                        continue;
                    new File(Util.IndexStorageDir + "/" + filelist[i]).delete();
                }
            }
            file = new File(Util.DataStorageDir);
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    if(!filelist[i].endsWith(".bin"))
                        continue;
                    new File(Util.DataStorageDir + filelist[i]).delete();
                }
            }
            file = new File(Util.MetaDataFile);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
