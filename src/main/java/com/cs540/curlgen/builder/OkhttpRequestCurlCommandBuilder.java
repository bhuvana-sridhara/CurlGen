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

import java.util.List;
import java.util.Map;

/**
 * Curl Command Builder to convert a OK HttpRequest to the Curl Command Object
 */
public class OkhttpRequestCurlCommandBuilder extends CurlCommandBuilder {
    private final Request okHttpRequest;

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
        Map<String, List<String>> headers = this.okHttpRequest.headers().toMultimap();
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            for (String value : header.getValue()) {
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
        this.curlCommand.addOption(Option.METHOD, this.okHttpRequest.method());
    }

    /**
     * Extract URL from the OK HttpRequest Object and set it to the curl command object
     *
     * @throws InvalidUrlFormatException
     */
    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.okHttpRequest.url().toString());
    }
}
