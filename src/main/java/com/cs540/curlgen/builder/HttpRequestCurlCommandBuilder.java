package com.cs540.curlgen.builder;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

/**
 * Curl Command Builder to convert a Java HttpRequest to the Curl Command Object
 */
public class HttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final Logger logger = LoggerFactory.getLogger(HttpRequestCurlCommandBuilder.class);
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
        logger.debug("Building Headers");
        Map<String, List<String>> headers = this.httpRequest.headers().map();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            for (String value : header.getValue()) {
                logger.debug(String.format("Setting Header %s with Value %s", header.getKey(), value));
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
            String timeout = String.valueOf(this.httpRequest.timeout().get().toSeconds());
            logger.debug(String.format("Setting Option %s with Value %s", Option.TIMEOUT, timeout));
            this.curlCommand.addOption(Option.TIMEOUT, timeout);
        }

        // Set the request method type as an option
        String method = this.httpRequest.method();
        logger.debug(String.format("Setting Option %s with Value %s", Option.METHOD, method));
        this.curlCommand.addOption(Option.METHOD, method);
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
