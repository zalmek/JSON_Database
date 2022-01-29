package server;

import com.beust.jcommander.Parameter;

public class JCommander_parser
{
    @Parameter(names = {"-t"}, description = "type")
    public String type;

    @Parameter(names = "-k", description = "key")
    public String key;

    @Parameter(names = "-v", description = "value")
    public String value;

    @Parameter(names = "-in" , description = "filename")
    public String filename;
}
