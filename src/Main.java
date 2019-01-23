import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        while (true) {
            try (ServerSocket server = new ServerSocket(51001)) {
                Socket s = server.accept();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject("Enter exist Login or \"reg\" for registration");

                ObjectInputStream ois = new ObjectInputStream(
                        s.getInputStream());
                String message = ois.readObject().toString();
                oos.close();
                ois.close();
                s.close();

                if ("reg".equals(message)) {

                    Socket s2 = server.accept();
                    ObjectOutputStream oos2 = new ObjectOutputStream(s2.getOutputStream());
                    oos2.writeObject("Registration accepted, enter message:");

                    ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream());
                    message = ois2.readObject().toString();
                    accounts.accountHolder.add(message);


                    System.out.println("User [" + message + "] is added");
                    oos2.close();
                    ois2.close();
                    s2.close();
                }
                if (accounts.accountHolder.contains(message)) {
                    System.out.println("User [" + message + "] is exist, Welcome!");
                    while (true) {
                        Socket s3 = server.accept();
                        ObjectInputStream ois3 = new ObjectInputStream(s3.getInputStream());
                        String secondMessage = ois3.readObject().toString();
                        System.out.println(message + ": " + secondMessage);

                        ObjectOutputStream oos3 = new ObjectOutputStream(s3.getOutputStream());
                        oos3.writeObject("Message sended, enter message:");


                        oos3.close();
                        ois3.close();
                        s3.close();
                        if ("exit".equals(secondMessage)) {
                            System.out.println("[" + message + "]" + " is exit from chat");
                            break;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


//
//
//            while(true){
//                Socket s = server.accept();
//                ObjectInputStream ois = new ObjectInputStream(
//                        s.getInputStream());
//                System.out.print(ois.readObject());
//                System.out.println(ois.readObject());
//                ois.close();
//                s.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}