package com.cs540.curlgen;

import com.cs540.curlgen.models.CurlCommand;
import com.typesafe.config.Config;

public class HttpRequestCurlCommandBuilder extends CurlCommandBuilder{

    private CurlCommand curlCommand;
    private Config curlConfig;

    @Override
    public CurlCommand GenerateCommand() {
        return super.GenerateCommand();
    }

    public HttpRequestCurlCommandBuilder() {
        super();
    }

    @Override
    public void buildHeaders() {

    }

    @Override
    public void buildOptions() {

    }


    @Override
    public void buildUrl(String url) {

    }


    public void buildRequestOption(String command) {

    }
}
