package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.params.HttpParams;


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


    @Override
    public void buildHeaders() {
        Header[] headers = this.httpRequest.getAllHeaders();
        for (Header header : headers) {
            this.curlCommand.addHeader(header.getName(), header.getValue());
        }
    }

    @Override
    public void buildOptions() {
        this.curlCommand.addOption(Option.TIMEOUT, String.valueOf(requestConfig.getConnectTimeout() / 1000));

        if (requestConfig.isContentCompressionEnabled()) {
            this.curlCommand.addOption(Option.COMPRESSION_ENABLED, "");
        }

        if (requestConfig.getProxy() != null) {
            this.curlCommand.addOption(Option.PROXY, requestConfig.getProxy().toURI());
        }


    }

    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.httpRequest.getRequestLine().getUri().toString());
    }
}
