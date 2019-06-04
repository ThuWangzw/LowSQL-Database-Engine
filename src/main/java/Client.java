import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public static void main(String[] args) {
        try {
            String importStmt = "import(\\s+)(\\S+)";
            Pattern importPtn = Pattern.compile(importStmt);
            String sqlStmt = "sql\\b";
            Pattern sqlPtn = Pattern.compile(sqlStmt);
            String exitStmt = "exit\\b";
            Pattern exitPtn = Pattern.compile(exitStmt);
            Socket socket = new Socket("127.0.0.1", 10086);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.println("import/sql/exit...");
                String mode = in.nextLine();
                if(importPtn.matcher(mode).find()){
                    Matcher matcher = importPtn.matcher(mode);
                    matcher.find();
                    FileInputStream instream = new FileInputStream(new File(matcher.group(2)));
                    byte[] bytes = new byte[instream.available()+1];
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    instream.read(bytes);
                    bytes[bytes.length-1] = -1;
                    for(byte i : bytes) bufferedWriter.write(i);
                    bufferedWriter.flush();
//                    socket.getOutputStream().close();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    StringBuffer content= new StringBuffer();
                    int ch;
                    while ((ch = bufferedReader.read()) < 65500) {
                        content.append((char) ch);
                    }
                    System.out.println(content.toString());
                }
                else if(sqlPtn.matcher(mode).find()){
                    while (true){
                        System.out.print('>');
                        String stmt = in.nextLine();
                        if(stmt.equals("exit")){
                            break;
                        }
                        byte[] bytes = stmt.getBytes();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        for(byte i : bytes) bufferedWriter.write(i);
                        bufferedWriter.write(-1);
                        bufferedWriter.flush();

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        StringBuffer content= new StringBuffer();
                        int ch;
                        while ((ch = bufferedReader.read()) < 65500) {
                            content.append((char) ch);
                        }
                        System.out.println(content.toString());
                    }
                }
                else if(exitPtn.matcher(mode).find()){
                    System.out.println("Bye~");
                    socket.getInputStream().close();
                    socket.getOutputStream().close();
                    socket.close();
                    break;
                }
                else {
                    System.out.println("Invalid input!");
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
