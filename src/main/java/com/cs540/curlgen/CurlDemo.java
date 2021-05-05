package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;


public class CurlDemo {

    public void userCode() {
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

            CurlGen obj = new CurlGen(new ApacheHttpRequestCurlCommandBuilder(config, httpRequest));
            System.out.println(obj.GetCurlCommand());
        } catch (InvalidUrlFormatException | IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CurlDemo demo = new CurlDemo();
        demo.userCode();
        System.out.println("done");
    }
}
