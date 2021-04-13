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
    private Map<String, String> options;
    private Map<String, String> headers;
    private List<String> locations;

    public CurlCommand() {
        this.options = new LinkedHashMap<>();
        this.headers = new LinkedHashMap<>();
        this.locations = new ArrayList<>(4);
    }

    public void setOptions(Map<String, String> options) {
        // TODO

        this.options = options;
    }

    public Map<String, String> addOption(String option, String value) {
        this.options.put(option, value); // TODO
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

    public String getUrl() {
        return url;
    }

    public Map<String, String> getOptions() {
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

    public void setUrl(String url) {
        this.url = url;
    }
}
