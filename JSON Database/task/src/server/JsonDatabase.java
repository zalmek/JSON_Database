package server;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class JsonDatabase {
    JsonObject database = new JsonObject();
    Gson gson = new GsonBuilder().create();

    public JsonDatabase() {
    }

    public String menu(String arg, JsonElement index) {
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

    public String menu(String arg, JsonElement key, JsonElement value) {
        return set(key, value);
    }

    String set(JsonElement pos, JsonElement value) {
        JsonObject data = database;
        if (pos.isJsonArray()) {

            JsonArray path = pos.getAsJsonArray();

            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    data.add(path.get(i).getAsString(), value);
                    break;
                } else if (data.has(path.get(i).getAsString())) {
                    if (!data.get(path.get(i).getAsString()).isJsonObject()) {
                        data.add(path.get(i).getAsString(), new JsonObject());
                    }
                } else {
                    data.add(path.get(i).getAsString(), new JsonObject());
                }
                data = data.getAsJsonObject(path.get(i).getAsString());
            }
        } else {
            database.add(pos.getAsString(), value);
        }

        writeDatabase();
        return okResponse();
    }

    private String get(JsonElement pos) {
        JsonObject data = new Gson().fromJson(database, JsonObject.class);
        if (pos.isJsonArray()) {
            JsonArray path = pos.getAsJsonArray();
            if (path.size() == 1) {
                if (!data.has(pos.getAsString())) {
                    return noSuchKeyResponse();
                } else {
                    writeDatabase();
                    return getResponse(data.get(path.get(0).getAsString()).getAsJsonObject());
                }
            } else {
                for (int i = 0; i < path.size(); i++) {
                    if (data.has(path.get(i).getAsString())) {
                        if (!data.get(path.get(i).getAsString()).isJsonObject()) {
                            if (i == path.size() - 1) {
                                return getResponse(data.get(path.get(i).getAsString()).getAsString());
                            } else {
                                return noSuchKeyResponse();
                            }
                        } else {
                            data = data.get(path.get(i).getAsString()).getAsJsonObject();
                        }
                    } else {
                        return noSuchKeyResponse();
                    }
                }
            }
        } else {
            if (!data.has(pos.getAsString())) {
                return noSuchKeyResponse();
            } else {
                return getResponse(new Gson().toJson(data.get(pos.getAsString())));
            }
        }

        return noSuchKeyResponse();
    }

    private String delete(JsonElement pos) {
        JsonObject data = database;
        if (pos.isJsonArray()) {
            JsonArray path = pos.getAsJsonArray();
            for (int i = 0; i < path.size(); i++) {
                if (!data.has(path.get(i).getAsString())) {
                    return noSuchKeyResponse();
                } else if (i == path.size() - 1) {
                    if (data.has(path.get(i).getAsString())) {
                        data.remove(path.get(i).getAsString());
                        break;
                    }
                } else if (!data.get(path.get(i).getAsString()).isJsonObject()) {
                    return noSuchKeyResponse();
                }
                data = data.getAsJsonObject(path.get(i).getAsString());
            }
        } else {
            if (!data.has(pos.getAsString())) {
                return noSuchKeyResponse();
            } else {
                database.remove(pos.getAsString());
            }
        }

        writeDatabase();
        return okResponse();
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
    private String getResponse(JsonObject value) {
        HashMap<String, Object> response_entity = new HashMap<>();
        response_entity.put("response", "OK");
        response_entity.put("value", value);
        return gson.toJson(response_entity);
    }
}
