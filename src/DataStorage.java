import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;


public class DataStorage{
    //Every table in a database has this instance
    private String DB_name;
    private String table_name;
    private RandomAccessFile raf;
    private long block_number;
    private TableSchema schema;

    public DataStorage(String db_name, String t_name, TableSchema sa){
        DB_name = db_name;
        schema = sa;
        table_name = t_name;
        OpenDataFile();
    }

    public void OpenDataFile(){
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            block_number = raf.length() / Util.DiskBlockSize;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void WriteDataBlock(DataBlock block){
        try{
            raf.seek(block.getPageId() * Util.DiskBlockSize);
            raf.write(block.getData(),0,Util.DiskBlockSize);
            //close raf and reopen it
            raf.close();
            OpenDataFile();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DataBlock ReadDataBlock(int page_id){
        if (page_id >= block_number)
            return new DataBlock((int)block_number,schema);
        byte[] data = new byte[Util.DiskBlockSize];
        try{
            raf.seek(page_id * Util.DiskBlockSize);
            raf.read(data,0,Util.DiskBlockSize);
            return new DataBlock(data,page_id,schema);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new DataBlock((int)block_number,schema);
    }

    public static void main(String[] args){
        System.out.println("-- DataStorage --");

    }


}