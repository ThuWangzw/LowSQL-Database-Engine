import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.io.File;
import java.io.RandomAccessFile;


public class IndexBuffer {
    static int INDEX_BUFFER_BLOCK_NUNMBER = 20000;
    BTreeNode[] index_buffer;
    DatabaseManager db;
    ArrayList<BTree> btrees;

    public IndexBuffer(DatabaseManager current_databse){
        db = current_databse;
        btrees = new ArrayList<>();
        index_buffer = new BTreeNode[INDEX_BUFFER_BLOCK_NUNMBER];
        loadIndexMetaData();
    }

    public void reload(DatabaseManager new_db){
        if(db.getDatabaseName().equals(new_db.getDatabaseName()))
            return;
        saveAll();
        clearBuffer();
        btrees = null;
        db = new_db;
        btrees = new ArrayList<>();
        loadIndexMetaData();
    }

    public void createIndex(String db_name,String table_name,TableSchema index_schema,int m) {
        BTree bt = new BTree(m,db_name, table_name, index_schema, this);
        saveIndexMetaData(bt);
        btrees.add(bt);
    }

    public void deleteIndex(String db_name,String table_name,TableSchema index_schema){
        BTree bt = getBTree(db_name,table_name,index_schema);
        if(bt == null)
            throw new NullPointerException("delete: Index not exist!");
        btrees.remove(bt);
        File file = new File(Util.IndexStorageDir + "/" + db_name + "_" + table_name + "_" + index_schema.concatNames()+".bin");
        if(!file.delete())
            throw new IllegalArgumentException("delete index file failed!");
        //clear all cached node that belongs to the index
        if(!db_name.equals(db.getDatabaseName()))
            return;
        TableSchema temp;
        for(int i = 0; i < INDEX_BUFFER_BLOCK_NUNMBER; i++){
            if(index_buffer[i] != null){
                temp = new TableSchema(index_buffer[i].table_name,index_buffer[i].index_attrs);
                if (index_buffer[i].DB_name.equals(db_name)
                        && index_buffer[i].table_name.equals(table_name)
                        && temp.concatNames().equals(index_schema.concatNames())){
                    index_buffer[i] = null;
                }
            }
        }
    }

    public void deleteIndex(BTree bt){
        btrees.remove(bt);
        File file = new File(Util.IndexStorageDir + "/" + bt.DB_name + "_" + bt.table_name + "_" + bt.index_schema.concatNames()+".bin");
        if(!file.delete())
            throw new IllegalArgumentException("delete index file failed!");
        //clear all cached node that belongs to the index
        if(!bt.DB_name.equals(db.getDatabaseName()))
            return;
        TableSchema temp;
        for(int i = 0; i < INDEX_BUFFER_BLOCK_NUNMBER; i++){
            if(index_buffer[i] != null){
                temp = new TableSchema(index_buffer[i].table_name,index_buffer[i].index_attrs);
                if (index_buffer[i].DB_name.equals(bt.DB_name)
                        && index_buffer[i].table_name.equals(bt.table_name)
                        && temp.concatNames().equals(bt.index_schema.concatNames())){
                    index_buffer[i] = null;
                }
            }
        }
    }

    public void deleteIndex(String db_name,String table_name){
        ArrayList<BTree> bts = getBTrees(db_name,table_name);
        for(BTree b : bts){
            deleteIndex(b);
        }
    }

    public void deleteIndex(String db_name){
        ArrayList<BTree> bts = getBTrees(db_name);
        for(BTree b : bts){
            deleteIndex(b);
        }
    }


    public void loadIndexMetaData(){
        try {
            File file = new File(Util.IndexStorageDir);
            byte[] data = new byte[Util.DiskBlockSize];
            String cur_db_name = db.getDatabaseName();
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    if(!filelist[i].endsWith(".bin"))
                        continue;
                    if(!filelist[i].startsWith(cur_db_name))
                        continue;
                    RandomAccessFile raf = new RandomAccessFile(Util.IndexStorageDir + "/" + filelist[i],"rw");
                    raf.seek(0);
                    raf.read(data,0,Util.DiskBlockSize);
                    BTree bt = new BTree(data,this,(int)raf.length()/Util.DiskBlockSize - 1);
                    btrees.add(bt);
                    raf.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveIndexMetaData(String d_name,String t_name,TableSchema index_attrs){
        BTree bt = getBTree(d_name,t_name,index_attrs);
        if (bt == null)
            return;
        if(bt.is_changed){
            try{
                RandomAccessFile raf = new RandomAccessFile(Util.IndexStorageDir + "/" + d_name + "_" + t_name + "_" + index_attrs.concatNames()+".bin","rw");
                //block_number = raf.length() / Util.DiskBlockSize;
                raf.seek(0);
                raf.write(bt.toByte(),0,Util.DiskBlockSize);
                raf.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void saveIndexMetaData(BTree bt){
        if(bt.is_changed){
            try{
                RandomAccessFile raf = new RandomAccessFile(Util.IndexStorageDir + "/" + bt.DB_name + "_" + bt.table_name + "_" + bt.index_schema.concatNames()+".bin","rw");
                //block_number = raf.length() / Util.DiskBlockSize;
                raf.seek(0);
                raf.write(bt.toByte(),0,Util.DiskBlockSize);
                raf.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<BTree> getBTrees(String d_name,String t_name){
        ArrayList<BTree> tree = new ArrayList<>();
        for(BTree cur : btrees) {
            if (cur.DB_name.equals(d_name) && cur.table_name.equals(t_name)) {
                tree.add(cur);
            }
        }
        return tree;
    }

    public ArrayList<BTree> getBTrees(String db_name){
        ArrayList<BTree> tree = new ArrayList<>();
        for(BTree cur : btrees) {
            if (cur.DB_name.equals(db_name)) {
                tree.add(cur);
            }
        }
        return tree;
    }


    public BTree getBTree(String d_name,String t_name,TableSchema index_attrs){
        for (BTree cur : btrees){
            if (cur.DB_name.equals(d_name) && cur.table_name.equals(t_name) && cur.index_schema.equal(index_attrs)) {
                return cur;
            }
        }
        return null;
    }

    public BTreeNode loadIndexBlockFromFile(String DB_name,String table_name,TableSchema index_attrs,int node_id,int M){
        try {
            byte[] data = new byte[Util.DiskBlockSize];
            RandomAccessFile raf = new RandomAccessFile(Util.IndexStorageDir + "/" + DB_name + "_" + table_name + "_" + index_attrs.concatNames()+".bin","rw");
            raf.seek(node_id * Util.DiskBlockSize);
            raf.read(data,0,Util.DiskBlockSize);
            BTreeNode temp;
            if (data[0] == 0){
                temp = new BTreeInternalNode(M,node_id,data,this,index_attrs.getAttrubutes(),DB_name,table_name);
            }else{
                temp = new BTreeLeafNode(M,node_id,data,this,index_attrs.getAttrubutes(),DB_name,table_name);
            }
            raf.close();
            //simplest strategy
            index_buffer[node_id] = temp;
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeIndexBlock(int node_id){
        if(index_buffer[node_id] == null)
            return;
        BTreeNode temp= index_buffer[node_id];
        TableSchema t = new TableSchema(temp.table_name,temp.index_attrs);
        if(!temp.is_changed)
            return;
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.IndexStorageDir + "/" + temp.DB_name + "_" + temp.table_name + "_" + t.concatNames()+".bin","rw");
            long block_number = raf.length()/Util.DiskBlockSize;
            if (temp.node_id >= block_number){
                long new_block_number = temp.node_id - block_number + 1;
                raf.seek(raf.length());
                byte[] empty_block = new byte[Util.DiskBlockSize];
                for(int i = 0; i < new_block_number; i++){
                    raf.write(empty_block,0,Util.DiskBlockSize);
                }
            }
            raf.seek(temp.node_id * Util.DiskBlockSize);
            raf.write(temp.index_data,0,Util.DiskBlockSize);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BTreeNode getNode(int node_id,String DB_name,String table_name,TableAttribute[] attrs){
        TableSchema temp = new TableSchema(table_name,attrs);
        BTree bt = getBTree(DB_name,table_name,temp);
        if(bt == null)
            return null;
        if(index_buffer[node_id] == null)
            return loadIndexBlockFromFile(DB_name,table_name,temp,node_id,bt.M);
        TableSchema temp2 = new TableSchema(index_buffer[node_id].table_name,index_buffer[node_id].index_attrs);
        if (!index_buffer[node_id].DB_name.equals(DB_name)
                || !index_buffer[node_id].table_name.equals(table_name)
                || !temp2.concatNames().equals(temp.concatNames())){
            writeIndexBlock(node_id);
            index_buffer[node_id] = null;
            return loadIndexBlockFromFile(DB_name,table_name,temp,node_id,bt.M);
        }
        return index_buffer[node_id];
    }


    public void addNewNode(BTreeNode node){
        if(index_buffer[node.node_id] != null){
            writeIndexBlock(node.node_id);
            index_buffer[node.node_id] = null;
        }
        index_buffer[node.node_id] = node;
        writeIndexBlock(node.node_id);
    }

    public void deleteNode(BTreeNode node){
        TableSchema temp = new TableSchema(index_buffer[node.node_id].table_name,index_buffer[node.node_id].index_attrs);
        BTree bt = getBTree(node.DB_name,node.table_name,temp);
        bt.addFreeBlockID((short)node.node_id);
        index_buffer[node.node_id] = null;
    }

    public void newRootNode(int new_root_id,String DB_name,String table_name,TableAttribute[] attrs){
        TableSchema temp = new TableSchema(table_name,attrs);
        BTree bt = getBTree(DB_name,table_name,temp);
        bt.root_id = new_root_id;
        bt.root_node = getNode(new_root_id,DB_name,table_name,attrs);
        saveIndexMetaData(bt);
        writeIndexBlock(new_root_id);
    }

    public int getFreeId(String DB_name,String table_name,TableAttribute[] attrs){
        TableSchema temp = new TableSchema(table_name,attrs);
        BTree bt = getBTree(DB_name,table_name,temp);
        return bt.getOneFreeBlockID();
    }


    public void saveAll(){
        for (BTree cur : btrees){
            saveIndexMetaData(cur);
        }

        for (int i = 0; i < INDEX_BUFFER_BLOCK_NUNMBER; i++){
            if (index_buffer[i] != null && index_buffer[i].is_changed){
                writeIndexBlock(i);
            }
        }
    }

    public void clearBuffer(){
        for (int i = 0; i < INDEX_BUFFER_BLOCK_NUNMBER; i++){
            index_buffer[i] = null;
        }
    }

}
