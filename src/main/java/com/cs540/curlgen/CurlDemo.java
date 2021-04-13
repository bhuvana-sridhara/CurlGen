package com.cs540.curlgen;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurlDemo {

    public void userCode(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .header("test", "hell yeah")
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        HttpRequestCurlCommandBuilder test = new HttpRequestCurlCommandBuilder();
    }

    public static void main(String[] args) {
        CurlDemo demo = new CurlDemo();
        demo.userCode();
        System.out.println("done");
    }

}
