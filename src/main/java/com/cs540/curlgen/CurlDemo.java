package com.cs540.curlgen;

import com.cs540.curlgen.builder.ApacheHttpRequestCurlCommandBuilder;
import com.cs540.curlgen.builder.CurlGen;
import com.cs540.curlgen.builder.HttpRequestCurlCommandBuilder;
import com.cs540.curlgen.builder.OkhttpRequestCurlCommandBuilder;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;


public class CurlDemo {

    private final Logger logger = LoggerFactory.getLogger(CurlDemo.class);

    public void userCode() {
        // Java Http Request
        logger.debug("java HttpRequest Demo");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .headers("key_1", "value_1", "key_2", "value_2")
                .header("test", "one")
                .timeout(Duration.ofMillis(1000))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(logger::debug)
                .join();

        try {
            // Create Curl Gen Object using HttpRequestCurlCommandBuilder object to convert the Java HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new HttpRequestCurlCommandBuilder(request));
            logger.info(String.format("Generated Curl Command: %s", obj.GetCurlCommand()));
        } catch (InvalidUrlFormatException ex) {
            logger.error(ex.getMessage(), ex);
        }


        //Apache Http
        try {
            logger.debug("Apache HttpRequest Demo");
            RequestConfig config = RequestConfig.custom().build();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpUriRequest httpRequest = RequestBuilder.get()
                    .setUri("http://www.google.com")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader("test", "one")
                    .setHeader("test", "two")
                    .build();

            CloseableHttpResponse httpResponse = httpclient.execute(httpRequest);

            logger.debug(String.format("Status: %s", httpResponse.getStatusLine()));
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(httpResponse.getEntity().getContent());
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            logger.debug(String.format("Response: %s", sb));

            // Create Curl Gen Object using ApacheHttpRequestCurlCommandBuilder object to convert the Apache HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new ApacheHttpRequestCurlCommandBuilder(config, httpRequest));
            logger.info(String.format("Generated Curl Command: %s", obj.GetCurlCommand()));
        } catch (InvalidUrlFormatException | IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

        // OkHTTP
        logger.debug("Ok HttpRequest Demo");
        OkHttpClient httpClient = new OkHttpClient();
        Request okHttpRequest = new Request.Builder()
                .url("http://www.httpbin.org/get")
                .addHeader("key_1", "value_1")
                .addHeader("key_2", "value_2")
                .build();
        httpClient.newCall(okHttpRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected response code " + response);

                    Headers responseHeaders = response.headers();
                    int size = responseHeaders.size();
                    for (int i = 0; i < size; i++) {
                        logger.debug(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    assert responseBody != null;
                    logger.debug(responseBody.string());
                }
            }
        });

        try {
            // Create Curl Gen Object using OkhttpRequestCurlCommandBuilder object to convert the Ok HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new OkhttpRequestCurlCommandBuilder(okHttpRequest));
            logger.info(String.format("Generated Curl Command: %s", obj.GetCurlCommand()));
        } catch (InvalidUrlFormatException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }


    public static void main(String[] args) {
        // The client class
        CurlDemo demo = new CurlDemo();

        // Run user code
        demo.userCode();
    }
}
