package com.cs540.curlgen.models;

import com.cs540.curlgen.builder.HttpRequestCurlCommandBuilder;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Curl Command Model class with headers, options and url as attributes
 * Exposes methods to set the attributes and the methods that generates the curl command
 * Can be used directly by the client if they have a json/xml etc. with url,headers data and need to generate curl command from that
 */
public class CurlCommand {

    private final Logger logger = LoggerFactory.getLogger(CurlCommand.class);

    private String url;
    private final Map<Option, String> options;
    private final Map<String, String> headers;

    public CurlCommand() {
        this.options = new LinkedHashMap<>();
        this.headers = new LinkedHashMap<>();
    }


    // Options Section

    /**
     * Add a map of option value pairs to the curl command
     *
     * @param options Map of Options and their values
     */
    public void addOptions(Map<Option, String> options) {
        for (Map.Entry<Option, String> option : options.entrySet()) {
            this.options.put(option.getKey(), option.getValue());
        }
    }

    /**
     * Add a option and it's value to the curl command
     *
     * @param option Option to add
     * @param value  value of Option to add
     */
    public void addOption(Option option, String value) {
        this.options.put(option, value);
    }

    /**
     * Fetch all options and their values from the curl command
     *
     * @return Option:Value map
     */
    public Map<Option, String> getOptions() {
        return this.options;
    }

    /**
     * Fetch a option's value from the curl command
     *
     * @param option the name of the option whose value is to be fetched
     * @return Value of the input option
     */
    public String getOption(Option option) {
        return this.options.getOrDefault(option, null);
    }

    /**
     * Remove a Header from the curl command object
     *
     * @param option the name of the option to be removed
     * @return The removed option's value
     */
    public String removeOption(Option option) {
        return this.options.remove(option);
    }


    // Headers Section

    /**
     * Add a map of header value pairs to the curl command
     *
     * @param headers Map of Headers and their values
     */
    public void addHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            this.headers.put(header.getKey(), header.getValue());
        }
    }

    /**
     * Add a header and it's value to the curl command
     *
     * @param header header to add
     * @param value  value of header to add
     */
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    /**
     * Fetch all headers and their values from the curl command
     *
     * @return Header:Value map
     */
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * Fetch a header's value from the curl command
     *
     * @param header the name of the header whose value is to be fetched
     * @return Value of the input header
     */
    public String getHeader(String header) {
        return this.headers.getOrDefault(header, null);
    }

    /**
     * Remove a Header from the curl command object
     *
     * @param header the name of the header to be removed
     * @return The removed header's value
     */
    public String removeHeader(String header) {
        return this.headers.remove(header);
    }


    /**
     * Set's the URL for the curl command
     *
     * @param url
     * @throws InvalidUrlFormatException
     */
    public void setUrl(String url) throws InvalidUrlFormatException {
        isValidUrl(url);
        this.url = url;
    }

    /**
     * Get the URL of the curl command
     *
     * @return Url of the curl command
     */
    public String getUrl() {
        return this.url;
    }


    /**
     * Generates the CurlCommand using the current object state (Attributes of URL Headers, Options)
     *
     * @return String: The Curl command
     */
    public String getCurlCommand() {
        StringBuilder cc = new StringBuilder();

        logger.debug("Generating Curl Command");

        // Append key work curl
        cc.append("curl ");

        // Adding all Headers to the curl command
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            logger.debug(String.format("Setting Header %s with value %s", header.getKey(), header.getValue()));
            cc.append(String.format("%s \"%s: %s\" ", Option.HEADER.value, header.getKey(), header.getValue()));
        }

        // Adding all other options to the curl command
        for (Map.Entry<Option, String> option : this.options.entrySet()) {
            logger.debug(String.format("Setting Option %s with value %s", option.getKey().value, option.getValue()));
            cc.append(String.format("%s %s ", option.getKey().value, option.getValue()));
        }

        logger.debug(String.format("Setting Curl Url as %s", url));
        cc.append(url);

        return cc.toString();
    }


    /**
     * Utility method to validate the url of the request
     *
     * @param url The URL hat needs validation
     * @return Boolean indicating whether url is valid or invalid
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