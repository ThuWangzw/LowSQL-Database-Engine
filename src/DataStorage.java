import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;


public class DataStorage{
    private String DB_name;
    private RandomAccessFile raf;
    private long block_number;
    private int[] field_type;

    public DataStorage(String db_name, int[] type){
        DB_name = db_name;
        field_type = type;
        OpenDataFile();
    }

    public void OpenDataFile(){
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
            //close raf and reopen it
            raf.close();
            OpenDataFile();
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
        Field fd3 = new Field("Hello",Util.VARCHAR);
        Field fd4 = new Field(64,Util.INT);
        Field fd5 = new Field("Yes",Util.VARCHAR);
        Field fd6 = new Field(10,Util.INT);
        Field[] fds = new Field[]{fd1,fd2};
        Field[] fds2  = new Field[]{fd3,fd4};
        Field[] fds3  = new Field[]{fd5,fd6};
        Record rc1 = new Record(fds);
        Record rc2 = new Record(fds2);
        Record rc3 = new Record(fds3);


        if(blk.insertOneRecord(rc1) && blk.insertOneRecord(rc2)){
            blk.updateOneRecord(0,rc3);
            ds.WriteDataBlock(blk);
            System.out.println("write success!");
        }

        DataBlock bk2 = new DataBlock(1);
        if(bk2.insertOneRecord(rc1)){
            ds.WriteDataBlock(bk2);
            System.out.println("write success!");
        }

        //read from file

        DataBlock blk2 = ds.ReadDataBlock(0);
        Record[] rds = blk2.extractAllRecords(ds.field_type);
        System.out.println("Read success!");

    }


}