package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CurlCommand {

    private static final Map<String, String> curlOptions = Map.of(
            "--location", "",
            "--request", "27",
            "--header", "10",
            "--data-raw", "51"
    );
    private final List<String> options = new ArrayList<String>();
    private final Map<String, String> headers = new LinkedHashMap<String, String>();
    private final List<String> locations = new ArrayList<String>(4);


    public String getCommand() {
        StringBuilder cc = new StringBuilder();


        return "";
    }
    
    public List<String> getOptions() {

        return options;
    }

    public Map<String, String> getHeaders() {
        //TODO
        return headers;
    }

    public List<String> getLocations() {
        // TODO
        return locations;
    }

    public String generateCurlString() {
        // TODO
        return "";
    }
}
