package server;

import com.google.gson.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
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
            //gson.toJson(msg,System.out);
            JsonObject map = JsonParser.parseString(msg).getAsJsonObject();

            //System.out.println("Received: " + msg);
            if (Objects.equals(map.get("type").getAsString(), "get")) {
                readLock.lock();
                String information = jsonDatabase.menu("get", map.get("key"));
                readLock.unlock();
                output.writeUTF(information);
            } else if (Objects.equals(map.get("type").getAsString(), "set")) {
                writeLock.lock();
                String information = jsonDatabase.menu("set", map.get("key"), map.get("value"));
                writeLock.unlock();
                output.writeUTF(information);
            } else if (Objects.equals(map.get("type").getAsString(), "delete")) {
                writeLock.lock();
                String information = jsonDatabase.menu("delete", map.get("key"));
                writeLock.unlock();
                output.writeUTF(information);
            } else if (Objects.equals(map.get("type").getAsString(), "exit")) {
                String information = jsonDatabase.menu("exit", new JsonPrimitive("-1"));
                output.writeUTF(information);
                exit_state=true;
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
