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
        for (DatabaseManager cur : databases){
            if(db_name.equals(cur.getDatabaseName())){
                databases.remove(cur);
                break;
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

    public static void main(String[] args){
        System.out.println("-- Server --");

        /*
        TableAttribute attr1 = new TableAttribute("height","name",Util.VARCHAR,20,true);
        TableAttribute attr2 = new TableAttribute("height","height",Util.INT,0,false);
        TableAttribute[] attrs = new TableAttribute[]{attr1,attr2};
        TableSchema schema = new TableSchema("height",attrs);
        TableManager tb1 = new TableManager("test","height",schema);*/

        Server server = new Server();
        //server.addDatabase(new DatabaseManager("db2"));
        //server.WriteMetaData();
        DataBlock blk = server.getOneDatabase("test").getOneTable("score").getDataStorage().ReadDataBlock(0);

        TableAttribute a1 = blk.getSchema().getOneAttribute("name");
        TableAttribute a2 = blk.getSchema().getOneAttribute("mark");

        Field fd1 = new Field("LeiYiran",a1);
        Field fd2 = new Field(95,a2);
        Field fd3 = new Field("WangZhaowei",a1);
        Field fd4 = new Field(99,a2);

        Field[] fds = new Field[]{fd1,fd2};
        Field[] fds2 = new Field[]{fd3,fd4};

        Record rcd = new Record(fds,blk.getSchema());
        Record rcd2 = new Record(fds2,blk.getSchema());

        blk.insertOneRecord(rcd);
        blk.insertOneRecord(rcd2);

        server.getOneDatabase("test").getOneTable("score").getDataStorage().WriteDataBlock(blk);
        System.out.println("-- Server --");
    }

}
