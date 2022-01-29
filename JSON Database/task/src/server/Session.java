package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Session extends Thread {
    private final Socket socket;
    JsonDatabase jsonDatabase;
    boolean exit_state = false;
    ReadWriteLock lock;
    Lock readLock;
    Lock writeLock;

    public Session(Socket socketForClient, JsonDatabase jsonDatabase) {
        this.socket = socketForClient;
        this.jsonDatabase = jsonDatabase;
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            String msg = input.readUTF();
            Gson gson = new GsonBuilder().create();
            gson.toJson(msg,System.out);
            JCommand_Json args = gson.fromJson(msg,JCommand_Json.class);

            //System.out.println("Received: " + msg);
            if (args.type.equals("get")) {
                readLock.lock();
                String information = jsonDatabase.menu("get", args.key.toString());
                readLock.unlock();
                output.writeUTF(information);
            } else if (args.type.equals("set")) {
                writeLock.lock();
                String information = jsonDatabase.menu("set", args.key.toString(), args.value.toString());
                writeLock.unlock();
                output.writeUTF(information);
            } else if (args.type.equals("delete")) {
                writeLock.lock();
                String information = jsonDatabase.menu("delete", args.key.toString());
                writeLock.unlock();
                output.writeUTF(information);
            } else if (args.type.equals("exit")) {
                String information = jsonDatabase.menu("exit", "-1");
                output.writeUTF(information);
                exit_state=true;
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
