package server;

import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        System.out.println("Server started!");
        JsonDatabase jsonDatabase = new JsonDatabase();
        String address = "127.0.0.1";
        int port = 23456;
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));) {
            while (true){
            Session session = new Session(server.accept(),jsonDatabase);
            session.start();
            session.join();
            if (session.exit_state){
                break;
            }
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
