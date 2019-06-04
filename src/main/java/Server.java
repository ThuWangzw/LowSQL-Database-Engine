import java.io.*;
import org.antlr.v4.runtime.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Server {
    private ArrayList<DatabaseManager> databases;
    private DatabaseManager current_database;
    IndexBuffer index_buffer;
    DataBuffer data_buffer;
    OutputStream writer;

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
            current_database = databases.get(0);
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
        if(current_database.getDatabaseName().equals(db_name))
            throw new IllegalArgumentException("Can not delete the running database");

        for(Iterator<DatabaseManager> it = databases.iterator(); it.hasNext(); ){
            DatabaseManager cur = it.next();
            if(db_name.equals(cur.getDatabaseName())){
                index_buffer.deleteIndex(db_name);
                data_buffer.deleteDataFile(db_name);
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
                index_buffer.reload(cur);
                data_buffer.reload(cur);
                return;
            }
        }
        throw new NoSuchElementException("Database " + db_name + "does not exist");
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

    public OutputStream getWriter() {
        return writer;
    }

    DatabaseManager getCurrentDatabase(){return current_database;}


    public static void main(String[] args) {
        System.out.println("server init..");
        Server server = new Server();

        System.out.println("server init done");
        while (true){
//            wait for client
            ServerSocket serverSocket;
            Socket socket;
            try {
                serverSocket = new ServerSocket(10086);
                System.out.println("Waiting for client...");
                socket = serverSocket.accept();
                System.out.println("Accept.");
            }
            catch (IOException e){
                System.out.println(e.getMessage());
                break;
            }
            try {
                InputStream in = socket.getInputStream();
                server.writer = socket.getOutputStream();
                Visitor visitor = new Visitor();
                visitor.setServer(server);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                while (true){
                    StringBuffer content= new StringBuffer();
                    int ch;
                    while ((ch = bufferedReader.read()) != 65535) {
                        content.append((char) ch);
                    }

                    ANTLRInputStream input = new ANTLRInputStream(content.toString());
                    LowSQLLexer lexer = new LowSQLLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    LowSQLParser parser = new LowSQLParser(tokens);
                    long start = System.currentTimeMillis();
                    ParseTree tree = parser.parse();
                    visitor.visit(tree);
                    long end = System.currentTimeMillis();
                    visitor.writer.write(("running time: "+String.valueOf((float) (end-start)/1000)+"s\r\n").getBytes());
                    visitor.writer.write(-1);
                    visitor.writer.flush();
                }
            }
            catch (IOException e){
                System.out.println("Connection failed");
            }
            catch (RuntimeException e){

                System.out.println(e.getMessage());
            }
            try {
                socket.close();
                serverSocket.close();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
            server.data_buffer.saveAll();
            server.index_buffer.saveAll();
        }



        //BTreeInternalNode n = new BTreeInternalNode();
        //n.getIndexData();
    }



}
