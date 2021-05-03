package com.cs540.curlgen.models;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlCommand {

    private String url;
    private final Map<Option, String> options;
    private final Map<String, String> headers;

    public CurlCommand() {
        this.options = new LinkedHashMap<>();
        this.headers = new LinkedHashMap<>();
    }


    // Options Section
    public void addOptions(Map<Option, String> options) {
        for (Map.Entry<Option, String> option : options.entrySet()) {
            this.options.put(option.getKey(), option.getValue());
        }
    }

    public void addOption(Option option, String value) {
        this.options.put(option, value);
    }

    public Map<Option, String> getOptions() {
        return this.options;
    }

    public String getOption(Option option) {
        return this.options.getOrDefault(option, null);
    }

    public String removeOption(Option option) {
        return this.options.remove(option);
    }


    // Headers Section
    public void addHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            this.headers.put(header.getKey(), header.getValue());
        }
    }

    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String header) {
        return this.headers.getOrDefault(header, null);
    }

    public String removeHeader(String header) {
        return this.headers.remove(header);
    }


    // URL Section
    public void setUrl(String url) throws InvalidUrlFormatException {
        isValidUrl(url);
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }


    /**
     * Generates the CurlCommand using the current object state (Attributes of URL Headers, Options)
     *
     * @return String: The Curl command
     */
    public String getCurlCommand() {
        // TODO: Main logic to build curl command
        StringBuilder cc = new StringBuilder();

        cc.append("curl ");

        // Adding all Headers to the curl command
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            cc.append(String.format("%s \"%s: %s\" ", Option.HEADER.value, header.getKey(), header.getValue()));
        }

        // Adding all other options to the curl command
        for (Map.Entry<Option, String> option : this.options.entrySet()) {
            cc.append(String.format("%s %s ", option.getKey().value, option.getValue()));
        }

        cc.append(url);


        return cc.toString();
    }


    /**
     * @param url
     * @return
     * @throws InvalidUrlFormatException
     */
    static boolean isValidUrl(String url) throws InvalidUrlFormatException {
        String regex = "((http|https)://)(www.)"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern pattern = Pattern.compile(regex);
        if (url == null) {
            throw new InvalidUrlFormatException("No URL Found. Please check the URL and run again");
        }
        Matcher matcher = pattern.matcher(url);
        if (!matcher.matches())
            throw new InvalidUrlFormatException("Bad URL. Please check the URL and run again. URL: " + url);
        else
            return true;
    }
}
