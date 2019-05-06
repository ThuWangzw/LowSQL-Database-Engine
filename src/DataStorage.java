import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;
import java.util.concurrent.ForkJoinPool;


public class DataStorage{
    private String DB_name;
    private RandomAccessFile raf;
    private long block_number;
    private int[] field_type;

    public DataStorage(String db_name, int[] type){
        DB_name = db_name;
        field_type = type;
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + ".txt","rw");
            block_number = raf.length() / Util.DiskBlockSize;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void WriteDataBlock(DataBlock block){
        try{
            raf.seek(block.getPageId() * Util.DiskBlockSize);
            raf.write(block.getData(),0,Util.DiskBlockSize);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DataBlock ReadDataBlock(int page_id){
        if (page_id >= block_number)
            return new DataBlock((int)block_number);
        byte[] data = new byte[Util.DiskBlockSize];
        try{
            raf.seek(page_id * Util.DiskBlockSize);
            raf.readFully(data,0,Util.DiskBlockSize);
            return new DataBlock(data,page_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new DataBlock((int)block_number);
    }

    public static void main(String[] args){
        System.out.println("-- DataStorage --");
        int[] type = new int[]{Util.STRING,Util.INT};
        DataStorage ds = new DataStorage("firstTry",type);

        //insert data into one block
        DataBlock blk = new DataBlock(0);
        Field fd1 = new Field("LeiYiran",Util.VARCHAR);
        Field fd2 = new Field(100,Util.INT);
        Field[] fds = new Field[]{fd1,fd2};
        Record rc1 = new Record(fds);
        if(blk.insertOneRecord(rc1)){
            ds.WriteDataBlock(blk);
            System.out.println("write success!");
        }

        //read from file
        DataBlock blk2 = ds.ReadDataBlock(0);
        Record rd = blk2.extractOneRecord(0,ds.field_type);
        rd.getValue();
        System.out.println("Read success!");


    }


}