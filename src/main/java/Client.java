import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        try{
            Random random = new Random();
//            create
            FileOutputStream createStream = new FileOutputStream(new File("test_create.sql"));
            String createStr = new String("create table avengers\n" +
                    "\t(id\t\t\tint not null, \n" +
                    "\t name\t\t\tstring(32) not null, \n" +
                    "\t power\tint not null,\n" +
                    "\t weight     float,\n" +
                    "\t height     double,\n" +
                    "\t primary key (ID)\n" +
                    "\t);");
            createStream.write(createStr.getBytes());
            createStream.close();
//            drop
            FileOutputStream dropStream = new FileOutputStream(new File("test_drop.sql"));
            String dropStr = new String("drop table avengers;");
            dropStream.write(dropStr.getBytes());
            dropStream.close();
//           insert
            FileOutputStream insertStream = new FileOutputStream(new File("test_insert.sql"));
            for(int i=0; i<10000; i++){
                String str = new String("INSERT INTO avengers VALUES ("+String.valueOf(i)+", 'NAME"+i+"',"+i+","+String.valueOf((float)Math.round(((float) i*1.1)*100)/100)+", "+String.valueOf((double)Math.round(((double) i*1.1)*100)/100)+");\r\n");
                insertStream.write(str.getBytes());
            }
            insertStream.close();
//            select
            FileOutputStream selectStream = new FileOutputStream(new File("test_select.sql"));
            String selectStr = new String("DELETE FROM avengers;");
            selectStream.write(selectStr.getBytes());
            selectStream.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());;
        }


//        try {
//            String importStmt = "import(\\s+)(\\S+)";
//            Pattern importPtn = Pattern.compile(importStmt);
//            String sqlStmt = "sql\\b";
//            Pattern sqlPtn = Pattern.compile(sqlStmt);
//            String exitStmt = "exit\\b";
//            Pattern exitPtn = Pattern.compile(exitStmt);
//            Socket socket = new Socket("127.0.0.1", 10086);
//            while (true){
//                Scanner in = new Scanner(System.in);
//                System.out.println("import/sql/exit...");
//                String mode = in.nextLine();
//                if(importPtn.matcher(mode).find()){
//                    Matcher matcher = importPtn.matcher(mode);
//                    matcher.find();
//                    FileInputStream instream = new FileInputStream(new File(matcher.group(2)));
//                    byte[] bytes = new byte[instream.available()+1];
//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                    instream.read(bytes);
//                    bytes[bytes.length-1] = -1;
//                    for(byte i : bytes) bufferedWriter.write(i);
//                    bufferedWriter.flush();
////                    socket.getOutputStream().close();
//
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    StringBuffer content= new StringBuffer();
//                    int ch;
//                    while ((ch = bufferedReader.read()) < 65500) {
//                        content.append((char) ch);
//                    }
//                    System.out.println(content.toString());
//                }
//                else if(sqlPtn.matcher(mode).find()){
//                    while (true){
//                        System.out.print('>');
//                        String stmt = in.nextLine();
//                        if(stmt.equals("exit")){
//                            break;
//                        }
//                        byte[] bytes = stmt.getBytes();
//                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        for(byte i : bytes) bufferedWriter.write(i);
//                        bufferedWriter.write(-1);
//                        bufferedWriter.flush();
//
//                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        StringBuffer content= new StringBuffer();
//                        int ch;
//                        while ((ch = bufferedReader.read()) < 65500) {
//                            content.append((char) ch);
//                        }
//                        System.out.println(content.toString());
//                    }
//                }
//                else if(exitPtn.matcher(mode).find()){
//                    System.out.println("Bye~");
//                    socket.getInputStream().close();
//                    socket.getOutputStream().close();
//                    socket.close();
//                    break;
//                }
//                else {
//                    System.out.println("Invalid input!");
//                }
//            }
//        }
//        catch (IOException e){
//            System.out.println(e.getMessage());
//        }
    }
}
