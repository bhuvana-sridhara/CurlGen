import com.cs540.curlgen.builder.ApacheHttpRequestCurlCommandBuilder;
import com.cs540.curlgen.builder.CurlGen;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

public class ApacheHttpRequestCurlCommandBuilderTest {
    @Test
    void ApacheHttpTest(){
        try {
            RequestConfig config = RequestConfig.custom().build();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpUriRequest httpRequest = RequestBuilder.get()
                    .setUri("http://www.google.com")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader("test", "one")
                    .setHeader("key_1", "value_1")
                    .setHeader("key_2", "value_2")
                    .build();

            CloseableHttpResponse httpResponse = httpclient.execute(httpRequest);

            System.out.println(httpResponse.getStatusLine());
            Scanner sc = new Scanner(httpResponse.getEntity().getContent());
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }

            CurlGen obj = new CurlGen(new ApacheHttpRequestCurlCommandBuilder(config, httpRequest));
            System.out.println(obj.GetCurlCommand());

            Assertions.assertTrue(obj.GetCurlCommand() != null);
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_1: value_1"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("key_2: value_2"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("test: one"));
            Assertions.assertTrue(obj.GetCurlCommand().contains("http://www.google.com"));

        } catch (InvalidUrlFormatException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
