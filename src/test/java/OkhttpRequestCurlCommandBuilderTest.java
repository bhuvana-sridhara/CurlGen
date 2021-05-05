import com.cs540.curlgen.CurlGen;
import com.cs540.curlgen.OkhttpRequestCurlCommandBuilder;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OkhttpRequestCurlCommandBuilderTest {

    @Test
    void okhttpRequestTest()

    {
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
                    System.out.println(responseBody.string());
                }
            }
        });
        try {
            CurlGen obj = new CurlGen(new OkhttpRequestCurlCommandBuilder(okHttpRequest));
            System.out.println(obj.GetCurlCommand());
            Assertions.assertTrue(obj.GetCurlCommand() != null);
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_1: value_1"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_2: value_2"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("http://www.httpbin.org/get"));
        } catch (InvalidUrlFormatException ex) {
            System.out.println(ex.toString());
        }
    }
}
