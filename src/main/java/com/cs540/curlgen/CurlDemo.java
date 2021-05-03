package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


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
    }


    public static void main(String[] args) {
        CurlDemo demo = new CurlDemo();
        demo.userCode();
        System.out.println("done");
    }
}
