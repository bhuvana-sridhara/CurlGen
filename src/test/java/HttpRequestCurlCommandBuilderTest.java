import com.cs540.curlgen.CurlGen;
import com.cs540.curlgen.HttpRequestCurlCommandBuilder;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.opentest4j.AssertionFailedError;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
@RunWith(JUnitPlatform.class)
public class HttpRequestCurlCommandBuilderTest {
    @Test
    void HttpRequestTest(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.google.com"))
                .headers("key_1", "value_1", "key_2", "value_2")
                .header("test", "one")
                .timeout(Duration.ofMillis(1000))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        try {
            CurlGen obj = new CurlGen(new HttpRequestCurlCommandBuilder(request));
            System.out.println(obj.GetCurlCommand());

            Assertions.assertTrue(obj.GetCurlCommand() != null);
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_1: value_1"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_2: value_2"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("test: one"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("http://www.google.com"));

        } catch (InvalidUrlFormatException ex) {
            System.out.println(ex.toString());
            Assertions.assertTrue(false);
        }

    }
}
