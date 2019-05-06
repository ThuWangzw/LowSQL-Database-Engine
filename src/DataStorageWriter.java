import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;

public class DataStorageWriter{
    //
    private String DB_name;
    private RandomAccessFile raf;

    public DataStorageWriter(String db_name){
        DB_name = db_name;
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + ".txt","rw");
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            raf.writeInt(1);
            raf.writeInt(2);
            raf.writeInt(3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Replace(){
        try {
            raf.seek(4);
            raf.writeInt(100);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        DataStorageWriter db_w = new DataStorageWriter("test");
        db_w.Replace();
        System.out.println("DataStorageWriter");
    }

}