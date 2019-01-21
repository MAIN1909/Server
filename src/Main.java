import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8899)){
            while(true){
                Socket s = server.accept();
                ObjectInputStream ois = new ObjectInputStream(
                        s.getInputStream());
                System.out.println(ois.readObject());
                ois.close();
                s.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
