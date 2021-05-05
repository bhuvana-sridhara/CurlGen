package com.cs540.curlgen.builder;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Curl Command Builder to convert a Apache HttpRequest to the Curl Command Object
 */
public class ApacheHttpRequestCurlCommandBuilder extends CurlCommandBuilder {

    private final Logger logger = LoggerFactory.getLogger(ApacheHttpRequestCurlCommandBuilder.class);
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
        logger.debug("Building Headers");
        Header[] headers = this.httpRequest.getAllHeaders();
        for (Header header : headers) {
            logger.debug(String.format("Setting Header %s with Value %s", header.getName(), header.getValue()));
            this.curlCommand.addHeader(header.getName(), header.getValue());
        }
    }

    /**
     * Extract the configuration from the Apache HttpRequest object and set it to the curl command object
     */
    @Override
    public void buildOptions() {
        // Set connection timeout option
        String timeout = String.valueOf(requestConfig.getConnectTimeout() / 1000);
        logger.debug(String.format("Setting Option %s with Value %s", Option.TIMEOUT, timeout));
        this.curlCommand.addOption(Option.TIMEOUT, timeout);

        // Set compression of response option
        if (requestConfig.isContentCompressionEnabled()) {
            logger.debug(String.format("Setting Option %s", Option.COMPRESSION_ENABLED));
            this.curlCommand.addOption(Option.COMPRESSION_ENABLED, "");
        }

        // Set proxy server option
        if (requestConfig.getProxy() != null) {
            String proxyServer = requestConfig.getProxy().toURI();
            logger.debug(String.format("Setting Option %s with Value %s", Option.PROXY, proxyServer));
            this.curlCommand.addOption(Option.PROXY, proxyServer);
        }
    }

    /**
     * Extract URL from the Apache HttpRequest Object and set it to te curl command object
     *
     * @throws InvalidUrlFormatException
     */
    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        String url = this.httpRequest.getRequestLine().getUri();
        logger.debug(String.format("Setting Url: %s ", url));
        this.curlCommand.setUrl(url);
    }
}
