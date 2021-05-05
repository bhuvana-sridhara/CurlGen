import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.CurlCommand;
import com.cs540.curlgen.models.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
@RunWith(JUnitPlatform.class)
public class CurlCommandTest {

    @Test
    void HeadersTest(){
        CurlCommand curlCommand = new CurlCommand();
        curlCommand.addHeader("test_1","value_1");
        Assertions.assertEquals(curlCommand.getHeaders().toString(),"{test_1=value_1}");
        Assertions.assertEquals(curlCommand.getHeader("test_1"),"value_1");

        curlCommand.removeHeader("test_1");
        Assertions.assertTrue(curlCommand.getHeaders().toString() == "{}");

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("test_1","value_1");
        curlCommand.addHeaders(headers);
        Assertions.assertTrue(curlCommand.getHeaders().equals(headers));
    }

    @Test
    void UrlTest(){
        CurlCommand curlCommand = new CurlCommand();
        try {
            curlCommand.setUrl("http://www.google.com");
        } catch (InvalidUrlFormatException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        Assertions.assertEquals(curlCommand.getUrl(),"http://www.google.com");
    }

    @Test
    void InvalidUrlTest(){
        CurlCommand curlCommand = new CurlCommand();
        try {
            curlCommand.setUrl("test");
        } catch (InvalidUrlFormatException e) {
            Assertions.assertTrue(curlCommand.getUrl() == null);

        }
    }

    @Test
    void OptionsTest(){
        CurlCommand curlCommand = new CurlCommand();
        curlCommand.addOption(Option.TIMEOUT, String.valueOf(1000));
        Assertions.assertEquals(curlCommand.getOptions().toString(),"{TIMEOUT=1000}");

        Map<Option, String> options = new LinkedHashMap<>();
        options.put(Option.DATA,"value_1");
        curlCommand.addOptions(options);

        curlCommand.removeOption(Option.TIMEOUT);
        Assertions.assertEquals(curlCommand.getOptions(),options);
    }
}
