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

//        System.out.println("URL TEST");
//        System.out.println(request.uri().toString());
//        System.out.println("URL TEST OVER");
//
//        System.out.println("HEADER TEST");
//        Map<String, List<String>> a = request.headers().map();
//        for (Map.Entry<String, List<String>> b : a.entrySet()) {
//            System.out.println(b.getKey());
//            System.out.println("List");
//            for (String c : b.getValue()) {
//                System.out.println(c);
//            }
//        }
//        System.out.println("HEADER TEST OVER");

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
