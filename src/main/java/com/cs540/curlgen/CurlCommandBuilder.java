package com.cs540.curlgen;

import com.cs540.curlgen.exceptions.InvalidUrlFormatException;
import com.cs540.curlgen.models.CurlCommand;

public abstract class CurlCommandBuilder implements ICurlCommandBuilder {

    protected CurlCommand curlCommand;


    public CurlCommandBuilder() {
        this.curlCommand = new CurlCommand();
    }


    public String GenerateCommand() {
        return this.curlCommand.getCurlCommand();
    }


    public abstract void buildHeaders();

    public abstract void buildOptions();

    public abstract void buildUrl() throws InvalidUrlFormatException;

}
