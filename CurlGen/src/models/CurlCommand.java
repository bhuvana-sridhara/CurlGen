package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CurlCommand {

    private CurlOptions curlOptions;
    private final List<String> options = new ArrayList<String>();
    private final Map<String, String> headers = new LinkedHashMap<String, String>();
    private final List<String> locations = new ArrayList<String>(4);


    public String getCommand() {
        StringBuilder cc = new StringBuilder();


        return "";
    }

    public CurlOptions getCurlOptions() {
        return curlOptions;
    }

    public void setCurlOptions(CurlOptions curlOptions) {
        this.curlOptions = curlOptions;
    }

    public List<String> getOptions() {
        return options;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public List<String> getLocations() {
        return locations;
    }
}
