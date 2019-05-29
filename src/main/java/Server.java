import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.FileOutputStream;
import java.util.Iterator;

public class Server {
    private ArrayList<DatabaseManager> databases;
    private DatabaseManager current_database;
    IndexBuffer index_buffer;
    DataBuffer data_buffer;


    public Server(){
        //load all database
        databases = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.MetaDataFile, "rw");
            long curpos = 0,end = raf.length();

            if(end > 0) {
                int db_size;
                while (curpos < end) {
                    raf.seek(curpos);
                    db_size = raf.readInt();
                    raf.seek(curpos);
                    byte[] temp = new byte[db_size];
                    raf.read(temp, 0, db_size);
                    databases.add(new DatabaseManager(temp));
                    curpos += db_size;
                }
                raf.close();
            }else{
                raf.close();
                //establish default database
                addDatabase(new DatabaseManager("test"));
                WriteMetaData();
            }
            setCurrentDatabase("test");
            //init index buffer
            index_buffer = new IndexBuffer(current_database);
            //init data buffer
            data_buffer = new DataBuffer(current_database);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void WriteMetaData(){
        try {
            FileOutputStream fp = new FileOutputStream(Util.MetaDataFile, false);
            int len = databases.size();
            for (int i = 0; i < len; i ++){
                fp.write(databases.get(i).toMetaByte());
            }
            fp.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addDatabase(DatabaseManager db){
        //check name repetition
        String db_name = db.getDatabaseName();
        for (DatabaseManager cur : databases){
            if(db_name.equals(cur.getDatabaseName()))
                return;
        }
        databases.add(db);
    }

    public void deleteDatabase(String db_name){
        for(Iterator<DatabaseManager> it = databases.iterator(); it.hasNext(); ){
            DatabaseManager cur = it.next();
            if(db_name.equals(cur.getDatabaseName())){
                it.remove();
                return;
            }
        }
    }

    public void setCurrentDatabase(String db_name){
        //choose the used database instance
        for(DatabaseManager cur:databases){
            if(db_name.equals(cur.getDatabaseName())){
                current_database = cur;
                //TODO
            }
        }
    }

    public ArrayList<DatabaseManager> getDatabases(){
        return databases;
    }

    public DatabaseManager getOneDatabase(String db_name){
        for (DatabaseManager cur : databases){
            if(db_name.equals(cur.getDatabaseName()))
                return cur;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("-- Node --");
        //BTreeInternalNode n = new BTreeInternalNode();
        //n.getIndexData();
    }



}
