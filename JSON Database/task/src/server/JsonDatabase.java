package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class JsonDatabase {
    HashMap<String, String> database;
    Gson gson = new GsonBuilder().create();

    public JsonDatabase() {
        database = new HashMap<>();
    }

    public String menu(String arg, String index) {
        switch (arg) {
            case "get":
                return get(index);
            case "delete":
                return delete(index);
            case "exit":
                return okResponse();
        }
        return "";
    }

    public String menu(String arg, String key, String value) {
        return set(key, value);
    }

    private String set(String key, String value) {
        database.put(key, value);
        writeDatabase();
        return okResponse();
    }

    private String get(String key) {
        if (!Objects.equals(database.get(key), "") && database.get(key) != null) {
            return getResponse(database.get(key));
        } else {
            return noSuchKeyResponse();
        }
    }

    private String delete(String key) {
        if (database.get(key) == null) {
            return noSuchKeyResponse();
        } else {
            database.remove(key);
            writeDatabase();
            return okResponse();
        }
    }

    public void writeDatabase() {
        File file = new File("./src/server/data/db.json");

        try {
            if (!file.exists())
            System.out.println(file.createNewFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (
                FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gson.toJson(database));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String okResponse() {
        HashMap<String, String> response_entity = new HashMap<>();
        response_entity.put("response", "OK");
        return gson.toJson(response_entity);
    }

    private String noSuchKeyResponse() {
        HashMap<String, String> response_entity = new HashMap<>();
        response_entity.put("response", "ERROR");
        response_entity.put("reason", "No such key");
        return gson.toJson(response_entity);
    }

    private String getResponse(String value) {
        HashMap<String, String> response_entity = new HashMap<>();
        response_entity.put("response", "OK");
        response_entity.put("value", value);
        return gson.toJson(response_entity);
    }
}
