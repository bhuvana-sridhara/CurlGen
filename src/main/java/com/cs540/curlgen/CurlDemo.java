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

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;


public class CurlDemo {

    public void userCode() {
        // Java Http Request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .headers("key_1", "value_1", "key_2", "value_2")
                .header("test", "one")
                .header("test", "two")
                .timeout(Duration.ofMillis(1000))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        try {
            // Create Curl Gen Object using HttpRequestCurlCommandBuilder object to convert the Java HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new HttpRequestCurlCommandBuilder(request));
            System.out.println(obj.GetCurlCommand());
        } catch (InvalidUrlFormatException ex) {
            System.out.println(ex.toString());
        }


        //Apache Http
        try {
            RequestConfig config = RequestConfig.custom().build();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpUriRequest httpRequest = RequestBuilder.get()
                    .setUri("http://www.google.com")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader("test", "one")
                    .setHeader("test", "two")
                    .build();

            CloseableHttpResponse httpResponse = httpclient.execute(httpRequest);

            System.out.println(httpResponse.getStatusLine());
            Scanner sc = new Scanner(httpResponse.getEntity().getContent());
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }

            // Create Curl Gen Object using ApacheHttpRequestCurlCommandBuilder object to convert the Apache HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new ApacheHttpRequestCurlCommandBuilder(config, httpRequest));
            System.out.println(obj.GetCurlCommand());
        } catch (InvalidUrlFormatException | IOException ex) {
            ex.printStackTrace();
        }

        // OkHTTP
        System.out.println("Okhttp");
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
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    assert responseBody != null;
                    System.out.println(responseBody.string());
                }
            }
        });

        try {
            // Create Curl Gen Object using OkhttpRequestCurlCommandBuilder object to convert the Ok HttpRequest Object to curl command
            CurlGen obj = new CurlGen(new OkhttpRequestCurlCommandBuilder(okHttpRequest));
            System.out.println(obj.GetCurlCommand());
        } catch (InvalidUrlFormatException ex) {
            System.out.println(ex.toString());
        }
    }


    public static void main(String[] args) {
        // The client class
        CurlDemo demo = new CurlDemo();

        // Run user code
        demo.userCode();
    }
}
