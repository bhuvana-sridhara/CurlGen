package com.cs540.curlgen;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.CurlCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlCommandBuilder implements ICurlCommandBuilder {

    private CurlCommand curlCommand;
    private Config curlConfig;

    public CurlCommandBuilder() {
        this.curlCommand = new CurlCommand();
        // We will be needing to extract a custom object and details from it

        this.curlConfig = ConfigFactory.parseResources("curloptions.conf");
    }

    @Override
    public CurlCommand GenerateCommand() {
        return this.curlCommand;
    }

    public void buildHeaders() {
        // TODO --header which is a MAP<String, String> and convert to JSON format
    }

    public void buildOptions() {
        //TODO --request GET POST PUT DELETE UPDATE
        if(true /* TODO the custom object contains a request type) */ )
        buildRequestOption("GET");

        // TODO --data-raw
    }

    private void buildRequestOption(String command) {

        curlCommand.addOption("--request", command );
    }

    public void buildUrl(String url) {
        // TODO add url
        try {
            isValidUrl(url);
        } catch (InvalidUrlFormatException e) {
            System.err.println(e);
            System.exit(-1);
        }


    }

    private static boolean isValidUrl(String url) throws InvalidUrlFormatException {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern pattern = Pattern.compile(regex);
        if (url == null) {
            throw new InvalidUrlFormatException("No URL Found. Please check the URL and run again");
        }
        Matcher matcher = pattern.matcher(url);
        if (!matcher.matches())
            throw new InvalidUrlFormatException("Bad URL. Please check the URL and run again. URL: " + url);
        else
            return true;
    }

}
