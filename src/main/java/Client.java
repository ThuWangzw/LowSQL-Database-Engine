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
            String exitStmt = "exit\\b";
            Pattern exitPtn = Pattern.compile(exitStmt);

            while (true){
                Socket socket = new Socket("127.0.0.1", 10086);
                Scanner in = new Scanner(System.in);
                System.out.println("import/exit...");
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

                    InputStream input = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    StringBuffer content= new StringBuffer();
                    int ch;
                    while ((ch = bufferedReader.read()) != -1) {
                        content.append((char) ch);
                    }
                    System.out.println(content.toString());
                }
                else if(exitPtn.matcher(mode).find()){
                    System.out.println("Bye~");
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
