package com.cs540.curlgen.builder;

/*
 *
 * Created by: prajw
 * Date: 03-May-21
 *
 */

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Curl Command Builder to convert a OK HttpRequest to the Curl Command Object
 */
public class OkhttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final Request okHttpRequest;
    private final Logger logger = LoggerFactory.getLogger(OkhttpRequestCurlCommandBuilder.class);

    public OkhttpRequestCurlCommandBuilder(Request okHttpRequest) throws InvalidUrlFormatException {
        super();
        this.okHttpRequest = okHttpRequest;
        buildHeaders();
        buildUrl();
        buildOptions();
    }

    /**
     * Extract the headers from the OK HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildHeaders() {
        logger.debug("Building Headers");
        Map<String, List<String>> headers = this.okHttpRequest.headers().toMultimap();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            for (String value : header.getValue()) {
                logger.debug(String.format("Setting Header %s with Value %s", header.getKey(), value));
                this.curlCommand.addHeader(header.getKey(), value);
            }
        }
    }

    /**
     * Extract the configuration from the OK HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildOptions() {
        // Set the request method type as an option
        String method = this.okHttpRequest.method();
        logger.debug(String.format("Setting Option %s with Value %s", Option.METHOD, method));
        this.curlCommand.addOption(Option.METHOD, method);
    }

    /**
     * Extract URL from the OK HttpRequest Object and set it to the curl command object
     *
     * @throws InvalidUrlFormatException
     */
    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        String url = this.okHttpRequest.url().toString();
        logger.debug(String.format("Setting Url: %s ", url));
        this.curlCommand.setUrl(url);
    }
}
