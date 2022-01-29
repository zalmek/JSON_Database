package server;

import com.beust.jcommander.Parameter;
import com.google.gson.JsonElement;

public class JCommand_Json {
    @Parameter(names = {"-t"}, description = "type")
    public String type;

    @Parameter(names = "-k", description = "key")
    public JsonElement key;

    @Parameter(names = "-v", description = "value")
    public JsonElement value;

    @Parameter(names = "-in" , description = "filename")
    public String filename;
}
