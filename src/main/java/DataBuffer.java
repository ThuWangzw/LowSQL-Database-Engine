import javax.xml.crypto.Data;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class DataBuffer {
    static int DATA_BUFFER_BLOCK_NUNMBER = 20000;
    DataBlock[] data_buffer;
    DatabaseManager db;
    ArrayList<DataStorage> storages;


    DataBuffer(DatabaseManager current_db){
        data_buffer = new DataBlock[DATA_BUFFER_BLOCK_NUNMBER];
        storages = new ArrayList<>();
        db = current_db;
        ArrayList<TableManager> tables = db.getTables();
        for (TableManager cur : tables){
            storages.add(new DataStorage(cur.getDBName(),cur.getTableName(),cur.getSchema(),this));
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
            //simplest strategy
            data_buffer[node_id % DATA_BUFFER_BLOCK_NUNMBER] = temp;
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
        int buffer_index = node_id % DATA_BUFFER_BLOCK_NUNMBER;
        if(data_buffer[buffer_index] == null)
            return loadDataBlockFromFile(DB_name,table_name,node_id);
        if (!data_buffer[buffer_index].DB_name.equals(DB_name)
                || !data_buffer[buffer_index].table_name.equals(table_name)){
            WriteDataBlock(buffer_index);
            data_buffer[buffer_index] = null;
            return loadDataBlockFromFile(DB_name,table_name,node_id);
        }
        return data_buffer[buffer_index];
    }


    public void WriteDataBlock(int node_id){
        int buffer_index = node_id % DATA_BUFFER_BLOCK_NUNMBER;
        DataBlock blk = data_buffer[buffer_index];
        if(!blk.is_revised)
            return;
        DataStorage ds = getDataStorage(blk.DB_name,blk.table_name);
        try{
            RandomAccessFile raf = new RandomAccessFile(Util.DataStorageDir + blk.DB_name + "_" + blk.table_name + ".bin","rw");
            if (blk.getPageId() >= ds.block_number){
                throw new IndexOutOfBoundsException("data block id is out of boundary");
            }
            //block_number = raf.length() / Util.DiskBlockSize;
            raf.seek(blk.getPageId() * Util.DiskBlockSize);
            raf.write(blk.getData(),0,Util.DiskBlockSize);
            raf.close();
            blk.is_revised = false;
        }catch(Exception e){
            e.printStackTrace();
        }
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
        for (int i = 0; i < DATA_BUFFER_BLOCK_NUNMBER; i++){
            if (data_buffer[i] != null && data_buffer[i].is_revised){
                WriteDataBlock(i);
            }
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
        for(int i = 0; i < DATA_BUFFER_BLOCK_NUNMBER; i++){
            if(data_buffer[i] != null){
                if (data_buffer[i].DB_name.equals(db_name) && data_buffer[i].table_name.equals(table_name)){
                    data_buffer[i] = null;
                }
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
        for(int i = 0; i < DATA_BUFFER_BLOCK_NUNMBER; i++){
            if(data_buffer[i] != null){
                if (data_buffer[i].DB_name.equals(ds.DB_name) && data_buffer[i].table_name.equals(ds.table_name)){
                    data_buffer[i] = null;
                }
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
        for(int i = 0; i < DATA_BUFFER_BLOCK_NUNMBER; i++){
            data_buffer[i] = null;
        }
    }

    public int getDataStorageRecordNum(String DB_name,String table_name){
        int recordNum=0;
        for(int i=0; i<getDataStorage(DB_name, table_name).block_number; i++){
            recordNum += getNode(DB_name, table_name, i).getRecordNumber();
        }
        return recordNum;
    }

}
