package com.cs540.curlgen;

/*
 *
 * Created by: prajw
 * Date: 03-May-21
 *
 */

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.Option;
import okhttp3.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class OkhttpRequestCurlCommandBuilder extends CurlCommandBuilder {
    private final Request okHttpRequest;

    public OkhttpRequestCurlCommandBuilder(Request okHttpRequest) throws InvalidUrlFormatException {
        super();
        this.okHttpRequest = okHttpRequest;
        buildHeaders();
        buildUrl();
        buildOptions();
    }

    @Override
    public void buildHeaders() {
        Map<String, List<String>> headers = this.okHttpRequest.headers().toMultimap();
        for(Map.Entry<String, List<String>> header: headers.entrySet()){
            for (String value : header.getValue()){
                this.curlCommand.addHeader(header.getKey(), value);
            }
        }

    }

    @Override
    public void buildOptions() {
        this.curlCommand.addOption(Option.METHOD, this.okHttpRequest.method());
    }

    @Override
    public void buildUrl() throws InvalidUrlFormatException {
        this.curlCommand.setUrl(this.okHttpRequest.url().toString());
    }
}
