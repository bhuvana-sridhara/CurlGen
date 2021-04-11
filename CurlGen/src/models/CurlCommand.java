package models;

import java.util.*;

public class CurlCommand {

    private static final Map<String, String> curlOptions = Map.of(
            "--location", "",
            "--request", "27",
            "--header", "10",
            "--data-raw", "51"
    );
    private String url;
    private Map<String, String> options = new LinkedHashMap<>();
    private Map<String, String> headers = new LinkedHashMap<>();
    private List<String> locations = new ArrayList<>(4);

    public void setOptions(Map<String, String> options) {
        // TODO
        this.options = options;
    }

    public Map<String, String> addOption(String option, String[] values) {
        this.options.put(option, values.toString());
        return options;
    }

    public void setHeaders(Map<String, String> headers) {
        // TODO
        this.headers = headers;
    }

    public void setLocations(List<String> locations) {
        // TODO
        this.locations = locations;
    }

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
