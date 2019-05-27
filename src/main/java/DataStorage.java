import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;


public class DataStorage{
    //Every table in a database has this instance
    private String DB_name;
    private String table_name;
    private RandomAccessFile raf;
    private long block_number;      //actual block number in the file
    private TableSchema schema;

    public DataStorage(String db_name, String t_name, TableSchema sa){
        DB_name = db_name;
        schema = sa;
        table_name = t_name;
        //if the the table is first established, create 1 block of the storage
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            if(raf.length() == 0){
                block_number = 0;
                WriteDataBlock(new DataBlock(0,schema));
            }else{
                block_number = raf.length() / Util.DiskBlockSize;
            }
            raf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void WriteDataBlock(DataBlock block){
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            if (block.getPageId() >= block_number){
                long new_block_number = block.getPageId() - block_number + 1;
                raf.seek(raf.length());
                DataBlock empty_block = new DataBlock(0,schema);
                for(int i = 0; i < new_block_number; i++){
                    raf.write(empty_block.getData(),0,Util.DiskBlockSize);
                }
                block_number += 1;
            }
            //block_number = raf.length() / Util.DiskBlockSize;
            raf.seek(block.getPageId() * Util.DiskBlockSize);
            raf.write(block.getData(),0,Util.DiskBlockSize);
            raf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DataBlock ReadDataBlock(int page_id){
        if (page_id >= block_number)
            return new DataBlock((int)block_number,schema);
        byte[] data = new byte[Util.DiskBlockSize];
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            raf.seek(page_id * Util.DiskBlockSize);
            raf.read(data,0,Util.DiskBlockSize);
            raf.close();
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