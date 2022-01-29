package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import server.JCommander_parser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("Client started!");
        String address = "127.0.0.1";
        int port = 23456;
        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            JCommander_parser arg = new JCommander_parser();
            JCommander.newBuilder().addObject(arg).build().parse(args);
            Gson gson = new Gson();
            if (arg.filename != null) {
                try {
                    String msg = new String(Files.readAllBytes(Paths.get("./src/client/data/" + arg.filename)));
                    output.writeUTF(msg);
                    System.out.println("Sent: " + msg);
                    String input_msg = input.readUTF();
                    System.out.println("Received: " + input_msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                output.writeUTF(gson.toJson(arg));
                System.out.println("Sent: " + gson.toJson(arg));
                String input_msg = input.readUTF();
                System.out.println("Received: " + input_msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
