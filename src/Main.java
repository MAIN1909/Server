import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static Set<String> names = new HashSet<>();

    static {
        names.add("Sanya");
        names.add("Alex");
        names.add("Dimon");
    }

    public static void main(String[] args) {
        new Main().server();
    }

    private void server() {
        synchronized (this) {
            try (ServerSocket server = new ServerSocket(8899)) {
                while (true) {
                    Socket s = server.accept();
                    ObjectInputStream ois = new ObjectInputStream(
                            s.getInputStream());
                    String firstMessage = (String) ois.readObject();
                    if ("registration".equals(firstMessage)) {
                        String name = (String) ois.readObject();
                        names.add(name);
                        System.out.println(name + ": " + ois.readObject());
                    } else if (names.contains(firstMessage)) {
                        System.out.println(firstMessage + ": " + ois.readObject());
                    }
                    ois.close();
                    s.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}