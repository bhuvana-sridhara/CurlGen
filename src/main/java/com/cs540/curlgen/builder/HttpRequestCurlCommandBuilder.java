package com.cs540.curlgen.builder;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

/**
 * Curl Command Builder to convert a Java HttpRequest to the Curl Command Object
 */
public class HttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final HttpRequest httpRequest;

    public HttpRequestCurlCommandBuilder(HttpRequest httpRequest) throws InvalidUrlFormatException {
        super();
        this.httpRequest = httpRequest;
        buildUrl();
        buildHeaders();
        buildOptions();
    }

    /**
     * Extract the headers from the Java HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildHeaders() {
        Map<String, List<String>> headers = this.httpRequest.headers().map();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            for (String value : header.getValue()) {

                this.curlCommand.addHeader(header.getKey(), value);
                // Adding only the first value from the list
                break;
            }
        }
    }

    /**
     * Extract the configuration from the OK HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildOptions() {
        if (this.httpRequest.timeout().isPresent()) {
            // Set connection timeout option
            this.curlCommand.addOption(Option.TIMEOUT, String.valueOf(this.httpRequest.timeout().get().toSeconds()));
        }

        // Set the request method type as an option
        this.curlCommand.addOption(Option.METHOD, this.httpRequest.method());
    }

    /**
     * Extract URL from the Java HttpRequest Object and set it to te curl command object
     *
     * @throws InvalidUrlFormatException
     */
    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.httpRequest.uri().toString());
    }
}
