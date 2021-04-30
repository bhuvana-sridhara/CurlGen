package com.cs540.curlgen;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.CurlCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CurlCommandBuilder implements ICurlCommandBuilder {

    protected CurlCommand curlCommand;
    protected Config curlConfig;


    public CurlCommandBuilder() {
        this.curlCommand = new CurlCommand();
        // We will be needing to extract a custom object and details from it

        // The below config object stores the curl commands in a HOCON file to model a curl command
        this.curlConfig = ConfigFactory.parseResources("curloptions.conf");
        //System.out.println(this.curlConfig.toString());
    }


    public String GenerateCommand() {
        return this.curlCommand.getCurlCommand();
    }


    public abstract void buildHeaders();

    public abstract void buildOptions();

    public abstract void buildUrl() throws InvalidUrlFormatException;


}
