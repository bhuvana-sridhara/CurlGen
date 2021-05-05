package com.cs540.curlgen.builder;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;

/**
 * Curl Command Builder to convert a Apache HttpRequest to the Curl Command Object
 */
public class ApacheHttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final HttpRequest httpRequest;
    private final RequestConfig requestConfig;

    public ApacheHttpRequestCurlCommandBuilder(RequestConfig requestConfig, HttpRequest httpRequest) throws InvalidUrlFormatException {
        super();
        this.httpRequest = httpRequest;
        this.requestConfig = requestConfig;
        buildUrl();
        buildHeaders();
        buildOptions();
    }

    /**
     * Extract the headers from the Apache HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildHeaders() {
        Header[] headers = this.httpRequest.getAllHeaders();
        for (Header header : headers) {
            this.curlCommand.addHeader(header.getName(), header.getValue());
        }
    }

    /**
     * Extract the configuration from the Apache HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildOptions() {
        // Set connection timeout option
        this.curlCommand.addOption(Option.TIMEOUT, String.valueOf(requestConfig.getConnectTimeout() / 1000));

        // Set compression of response option
        if (requestConfig.isContentCompressionEnabled()) {
            this.curlCommand.addOption(Option.COMPRESSION_ENABLED, "");
        }

        // Set proxy server option
        if (requestConfig.getProxy() != null) {
            this.curlCommand.addOption(Option.PROXY, requestConfig.getProxy().toURI());
        }
    }

    /**
     * Extract URL from the Apache HttpRequest Object and set it to te curl command object
     *
     * @throws InvalidUrlFormatException
     */
    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.httpRequest.getRequestLine().getUri());
    }
}
