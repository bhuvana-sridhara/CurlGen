package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

public class HttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final HttpRequest httpRequest;

    public HttpRequestCurlCommandBuilder(HttpRequest httpRequest) throws InvalidUrlFormatException {
        super();
        this.httpRequest = httpRequest;
        buildUrl();
        buildHeaders();
        buildOptions();
    }

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

    @Override
    public void buildOptions() {
        if (this.httpRequest.timeout().isPresent()) {
            // Add timeout option
            this.curlCommand.addOption(Option.TIMEOUT, String.valueOf(this.httpRequest.timeout().get().toSeconds()));
        }

        // Set the request method type as an option
        this.curlCommand.addOption(Option.METHOD, this.httpRequest.method());
    }

    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.httpRequest.uri().toString());
    }
}
