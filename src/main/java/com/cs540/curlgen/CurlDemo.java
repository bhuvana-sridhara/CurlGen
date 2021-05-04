package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
                try (ResponseBody responseBody = response.body()){
                    if (!response.isSuccessful()) throw new IOException("Unexpected response code " + response);

                    Headers responseHeaders = response.headers();
                    int size = responseHeaders.size();
                    for(int i = 0;i<size;i++){
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(responseBody.string());
                }
            }
        });

        try {
            CurlGen obj = new CurlGen(new OkhttpRequestCurlCommandBuilder(okHttpRequest));
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
