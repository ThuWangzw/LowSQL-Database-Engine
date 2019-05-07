import java.util.ArrayList;
import java.io.RandomAccessFile;

public class Server {
    private ArrayList<DatabaseManager> databases;

    public Server(){
        databases = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.MetaDataFile, "rw");
            long curpos = 0,end = raf.length();
            int db_size;
            while(curpos < end - 1){
                raf.seek(curpos);
                db_size = raf.readInt();
                raf.seek(curpos);
                byte[] temp = new byte[db_size];
                raf.read(temp,0,db_size);
                databases.add(new DatabaseManager(temp));
                curpos += db_size;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println("-- Server --");

    }

}
