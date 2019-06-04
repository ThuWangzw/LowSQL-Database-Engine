import javax.xml.crypto.Data;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class DataBuffer {
    static int DATA_BUFFER_BLOCK_NUNMBER = 20000;
    LRUCache<String,DataBlock> buffer;
    DatabaseManager db;
    ArrayList<DataStorage> storages;


    DataBuffer(DatabaseManager current_db){
        storages = new ArrayList<>();
        db = current_db;
        ArrayList<TableManager> tables = db.getTables();
        for (TableManager cur : tables){
            storages.add(new DataStorage(cur.getDBName(),cur.getTableName(),cur.getSchema(),this));
        }
        //LRU buffer
        float load_factor = (float)0.75;
        int capacity = (int) Math.ceil(DATA_BUFFER_BLOCK_NUNMBER / load_factor) + 1;
        buffer = new LRUCache<>(capacity,load_factor,true,DATA_BUFFER_BLOCK_NUNMBER);
        //preload
        preload();
    }

    public void preload(){
        for(DataStorage d: storages){
            for(int i = 0; i < d.block_number;i++)
                getNode(d.DB_name,d.table_name,i);
        }
    }

    void reload(DatabaseManager new_db){
        if(new_db.getDatabaseName().equals(db.getDatabaseName()))
            return;
        saveAll();
        clearBuffer();
        //reload
        db = new_db;
        storages = null;
        storages = new ArrayList<>();
        ArrayList<TableManager> tables = db.getTables();
        for (TableManager cur : tables){
            storages.add(new DataStorage(cur.getDBName(),cur.getTableName(),cur.getSchema(),this));
        }
        preload();
    }

    public void addDataStorage(TableManager table){
        storages.add(new DataStorage(table.getDBName(),table.getTableName(),table.getSchema(),this));
    }

    public DataStorage getDataStorage(String d_name,String t_name){
        for (DataStorage cur : storages){
            if (cur.DB_name.equals(d_name) && cur.table_name.equals(t_name)) {
                return cur;
            }
        }
        return null;
    }

    public ArrayList<DataStorage> getDataStorages(String d_name){
        ArrayList<DataStorage> dss = new ArrayList<>();
        for (DataStorage cur : storages){
            if (cur.DB_name.equals(d_name)) {
                dss.add(cur);
            }
        }
        return dss;
    }

    public DataBlock loadDataBlockFromFile(String DB_name,String table_name,int node_id){
        try {
            DataStorage st = getDataStorage(DB_name,table_name);
            if (node_id >= st.block_number)
                throw new IndexOutOfBoundsException("The node id is out of boundary");
            byte[] data = new byte[Util.DiskBlockSize];
            RandomAccessFile raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            raf.seek(node_id * Util.DiskBlockSize);
            raf.read(data,0,Util.DiskBlockSize);
            DataBlock temp = new DataBlock(DB_name,table_name,data,node_id,st.schema);
            raf.close();
            add(name2key(node_id,DB_name,table_name),temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Can't load data from data file");
    }


    public DataBlock getNode(String DB_name,String table_name,int node_id){
        DataStorage st = getDataStorage(DB_name,table_name);
        if(st == null)
            throw new NullPointerException("Table not exists in current database");
        DataBlock search_block = buffer.get(name2key(node_id,DB_name,table_name));
        if(search_block == null)
            return loadDataBlockFromFile(DB_name,table_name,node_id);
        return search_block;
    }

    public void appendDataBlock(String DB_name,String table_name){
        DataStorage st = getDataStorage(DB_name,table_name);
        if(st == null)
            throw new NullPointerException("Table not exists in current database");
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin", "rw");
            raf.seek(raf.length());
            DataBlock empty_block = new DataBlock(DB_name,table_name,0,st.schema);
            raf.write(empty_block.getData(),0,Util.DiskBlockSize);
            st.block_number += 1;
            raf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void appendDataBlock(DataStorage st){
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.DataStorageDir + st.DB_name + "_" + st.table_name + ".bin", "rw");
            raf.seek(raf.length());
            DataBlock empty_block = new DataBlock(st.DB_name, st.table_name, 0, st.schema);
            raf.write(empty_block.getData(), 0, Util.DiskBlockSize);
            st.block_number += 1;
            raf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveAll(){
        Iterator<Map.Entry<String,DataBlock>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,DataBlock> next = iterator.next();
            next.getValue().WriteDataBlock();
        }
    }

    public void deleteDataFile(String db_name,String table_name){
        DataStorage ds = getDataStorage(db_name,table_name);
        if(ds == null)
            throw new NullPointerException("delete: Table not exist!");
        storages.remove(ds);
        File file = new File(Util.DataStorageDir + db_name + "_" + table_name + ".bin");
        if(!file.delete())
            throw new IllegalArgumentException("delete data file failed!");

        if(!db_name.equals(db.getDatabaseName()))
            return;

        DataBlock cur;
        Iterator<Map.Entry<String,DataBlock>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,DataBlock> next = iterator.next();
            cur = next.getValue();
            if (cur.DB_name.equals(db_name) && cur.table_name.equals(table_name)){
                buffer.remove(next.getKey());
            }
        }
    }

    public void deleteDataFile(DataStorage ds){
        storages.remove(ds);
        File file = new File(Util.DataStorageDir + ds.DB_name + "_" + ds.table_name + ".bin");
        if(!file.delete())
            throw new IllegalArgumentException("delete data file failed!");

        if(!ds.DB_name.equals(db.getDatabaseName()))
            return;

        DataBlock cur;
        Iterator<Map.Entry<String,DataBlock>> iterator= buffer.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,DataBlock> next = iterator.next();
            cur = next.getValue();
            if (cur.DB_name.equals(ds.DB_name) && cur.table_name.equals(ds.table_name)){
                buffer.remove(next.getKey());
            }
        }
    }

    public void deleteDataFile(String db_name){
        ArrayList<DataStorage> dss = getDataStorages(db_name);
        for (DataStorage ds : dss){
            deleteDataFile(ds);
        }
    }

    public void clearBuffer(){
        buffer.clear();
    }

    public int getDataStorageRecordNum(String DB_name,String table_name){
        int recordNum=0;
        long blk_number = getDataStorage(DB_name, table_name).block_number;
        for(int i=0; i< blk_number; i++){
            recordNum += getNode(DB_name, table_name, i).getRecordNumber();
        }
        return recordNum;
    }

    public void add(String key, DataBlock value){
        if(buffer.removeOldest()){
            buffer.getOldest().WriteDataBlock();
        }
        buffer.put(key,value);
    }

    public String name2key(int node_id,String DB_name,String table_name){
        return DB_name + table_name + String.valueOf(node_id);
    }
}
