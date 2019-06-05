import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.io.RandomAccessFile;


public class IndexBuffer {
    static int INDEX_BUFFER_BLOCK_NUNMBER = 20000;
    DatabaseManager db;
    ArrayList<BTree> btrees;
    LRUCache<String,BTreeNode> buffer;

    public IndexBuffer(DatabaseManager current_databse){
        db = current_databse;
        btrees = new ArrayList<>();
        loadIndexMetaData();
        //LRU Buffer
        float load_factor = (float)0.75;
        int capacity = (int) Math.ceil(INDEX_BUFFER_BLOCK_NUNMBER / load_factor) + 1;
        buffer = new LRUCache<>(capacity,load_factor,true,INDEX_BUFFER_BLOCK_NUNMBER);
        preload();
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
        preload();
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
        BTreeNode cur;
        Iterator<Map.Entry<String,BTreeNode>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,BTreeNode> next = iterator.next();
            cur = next.getValue();
            temp = new TableSchema(cur.table_name,cur.index_attrs);
            if (cur.DB_name.equals(db_name) && cur.table_name.equals(table_name) && temp.concatNames().equals(index_schema.concatNames())){
                iterator.remove();
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
        BTreeNode cur;
        Iterator<Map.Entry<String,BTreeNode>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,BTreeNode> next = iterator.next();
            cur = next.getValue();
            temp = new TableSchema(cur.table_name,cur.index_attrs);
            if (cur.DB_name.equals(bt.DB_name) && cur.table_name.equals(bt.table_name) && temp.concatNames().equals(bt.index_schema.concatNames())){
                iterator.remove();
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


    public void preload(){
        for(BTree b: btrees){
            for(int i = 1; i <= b.node_block_number;i++)
            getNode(i,b.DB_name,b.table_name,b.index_schema.getAttrubutes());
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

            add(name2key(node_id,DB_name,table_name,index_attrs),temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public BTreeNode getNode(int node_id,String DB_name,String table_name,TableAttribute[] attrs){
        TableSchema temp = new TableSchema(table_name,attrs);
        BTree bt = getBTree(DB_name,table_name,temp);
        if(bt == null)
            return null;
        BTreeNode search_node = (BTreeNode) buffer.get(name2key(node_id,DB_name,table_name,temp));
        if(search_node == null)
            return loadIndexBlockFromFile(DB_name,table_name,temp,node_id,bt.M);
        return search_node;
    }


    public void addNewNode(BTreeNode node){
        TableSchema temp = new TableSchema(node.table_name,node.index_attrs);
        add(name2key(node.node_id,node.DB_name,node.table_name,temp),node);
    }

    public void deleteNode(BTreeNode node){
        TableSchema temp = new TableSchema(node.table_name,node.index_attrs);
        Iterator<Map.Entry<String,BTreeNode>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,BTreeNode> next = iterator.next();
            if(next.getKey().equals(name2key(node.node_id,node.DB_name,node.table_name,temp))){
                iterator.remove();
            }
        }
        BTree bt = getBTree(node.DB_name,node.table_name,temp);
        bt.addFreeBlockID((short)node.node_id);
    }

    public void newRootNode(int new_root_id,String DB_name,String table_name,TableAttribute[] attrs){
        TableSchema temp = new TableSchema(table_name,attrs);
        BTree bt = getBTree(DB_name,table_name,temp);
        bt.root_id = new_root_id;
        saveIndexMetaData(bt);
        BTreeNode root_node = (BTreeNode) getNode(new_root_id,DB_name,table_name,attrs);
        if(root_node == null){
            throw new IllegalArgumentException("root node does not exist!");
        }
        root_node.WriteIndexBlock();
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
        Iterator<Map.Entry<String,BTreeNode>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,BTreeNode> next = iterator.next();
            next.getValue().WriteIndexBlock();
        }
    }

    public void clearBuffer(){
        buffer.clear();
    }

    public void add(String key,BTreeNode value){
        if(buffer.removeOldest()){
            buffer.getOldest().WriteIndexBlock();
        }
        buffer.put(key,value);
    }

    public String name2key(int node_id,String DB_name,String table_name,TableSchema attrs){
        return DB_name + table_name + attrs.concatNames() + String.valueOf(node_id);
    }

}
