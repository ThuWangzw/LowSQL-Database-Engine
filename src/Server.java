import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.FileOutputStream;

public class Server {
    private ArrayList<DatabaseManager> databases;
    private DatabaseManager current_database;

    public Server(){
        //load all database
        databases = new ArrayList<>();
        try {
            RandomAccessFile raf = new RandomAccessFile(Util.MetaDataFile, "rw");
            long curpos = 0,end = raf.length();

            if(end == 0) {
                int db_size;
                while (curpos < end - 1) {
                    raf.seek(curpos);
                    db_size = raf.readInt();
                    raf.seek(curpos);
                    byte[] temp = new byte[db_size];
                    raf.read(temp, 0, db_size);
                    databases.add(new DatabaseManager(temp));
                    curpos += db_size;
                    raf.close();
                }
            }else{
                raf.close();
                //establish default database
                addDatabase(new DatabaseManager("test"));
                WriteMetaData();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Server(ArrayList<DatabaseManager> db) {
        databases = db;
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
            if(cur.getDatabaseName() == db_name)
                return;
        }
        databases.add(db);
    }

    public void deleteDatabase(String db_name){
        int len = databases.size();
        for (int i = 0; i < len; i ++){
            if(databases.get(i).getDatabaseName() == db_name){
                databases.remove(i);
                break;
            }
        }
    }

    public void setCurrentDatabase(String db_name){
        //choose the used database instance
        for(DatabaseManager cur:databases){
            if(cur.getDatabaseName() == db_name){
                current_database = cur;
                //TODO
            }
        }
    }

    public static void main(String[] args){
        System.out.println("-- Server --");

        Server server = new Server();
        //create db
        /*TableAttribute attr1 = new TableAttribute("score","name",Util.VARCHAR,20,true);
        TableAttribute attr2 = new TableAttribute("score","mark",Util.INT,0,false);
        TableAttribute[] attrs = new TableAttribute[]{attr1,attr2};
        TableSchema schema = new TableSchema("score",attrs);
        TableManager tb1 = new TableManager("test","score",schema);
        DatabaseManager*/

    }

}
