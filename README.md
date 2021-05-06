# CurlGen
Automatic cURLcommand generator library

### Team: 
* Bhuvana Sridhara bsridh5@uic.edu
* Pramodh Acharya pachar7@uic.edu
* Prajwal Kammardi pkamma2@uic.edu

### Contents

* [Requirements](#requirements)
* [Building the code](#building-the-code) 
* [Description](#description)
 	* [HttpRequest](#query-builder)
	* [HttpUriRequest](#http-client)
	* [OkHttpClient](#scala-models)
* [Components](#components)
* [Usage](#usage)

### Requirements
* [Intellij](https://www.jetbrains.com/idea/)
* [SBT](https://www.scala-sbt.org/)

### Building the code
Run the following from the command line - 

* To test the code, run ```sbt test```
* To compile the code, run ```sbt compile ```
* To clean working directory, run ```sbt clean```
* You can chain the tasks using ```sbt clean compile test```
* To use the library developed in this project, you can clone the repository and open the project in Intellij and run the commands from a main function using ```sbt run```. You can also run the code by opening the sbt shell in Intellij and typing ```run```
* To add the library to an existing project, you can download the .jar file linked and add it as an external dependency in your project. 

### Description

CurlGen provides a programmatic way to generate cURL commands from native requests. This library can be included in any Java project and can generate cURL commands automatically from HTTP requests. The user can also manually specify URL, headers, options etc if they wish and generate a cURL command manually.

Workflow diagram of the library.

![alt_text](https://i.postimg.cc/NMVpyhLV/Screen-Shot-2021-05-05-at-5-52-15-PM.png)

The library offers support for the following :

* HttpRequest (https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html)
* HttpUriRequest (https://javadoc.io/doc/org.apache.httpcomponents/httpclient/4.5.1/org/apache/http/client/methods/HttpUriRequest.html)
* OkHttpClient (https://javadoc.io/doc/com.squareup.okhttp3/okhttp/3.3.1/okhttp3/OkHttpClient.html)

### Components
The main components of the library are defined as follows:
To jump straight to usage, click [here](#usage)!

The library makes use of the _builder_ design pattern.

![alt_text](Documents/images/uml.png)

#### CurlGen

This class helps in the process required to construct the cURL commands in a specific order. The order in which the different parts of the cURL command is directed by CUrlGen. The final cURL command is still returned by the CurlCommandBuilder. Client will use this class and pass the respective builder to generate and fetch the cURL command.

#### CurlCommand:

This class is a model representing the CurlCommand.  It shall contain methods to set URLs, Headers, Options, add Options. This is a class representation of the cURL command in the particular language.

#### ICurlCommandBuilder:

This interface defines the method to be used to build the Curl Command, GenerateCommand() in this case.

#### CurlCommandBuilder:

This class is responsible for building the curl command. It is an implementation of the Interface ICurlCommandBuilder. Implements the GenerateCurlCommand() which contains logic to convert model class CurlCommand to the final cURL command.

#### Native Request Builders: (HttpRequestCurlCommandBuilder, ApacheHttpRequestCurlCommandBuilder, OkhttpRequestCurlCommandBuilder):

These classes will be used to convert respective Request objects to the cURL command. They extend the CurlCommandBuilder.

#### Option:

This is an enumerator to map the Option's generic name to the curl option name. Around 29 options are supported as of now.

### Usage

Download the .jar file (cs540_bhuvana_sridhara_project_curlgen_2.12-0.1.0-SNAPSHOT)

Set the jar as a module dependency to your project by the following procedure: 

* Start from the "project window";
* Select the main Java module;
* Use menu File | Project Structure;
* In Project Settings, select Modules, and select your Java application as interested module in the middle window;
* On the window to the right, select "Dependencies" tab, and press "+" and select "Jars or directories" and navigate to the jar file, add select it so the custom jar file is added as a new entry in the window, check the Export checkbox and set Scope pulldown set as "Compile";
* Press OK.
* Use a class defined in the custom jar file as required

Create your request object.

If using HttpRequest, use HttpRequestCurlCommandBuilder:
```
CurlGen obj = new CurlGen(new HttpRequestCurlCommandBuilder(request));
```

If using HttpUriRequest, use ApacheHttpRequestCurlCommandBuilder:
```
CurlGen obj = new CurlGen(new ApacheHttpRequestCurlCommandBuilder(config, httpRequest));
```

If using OkHttpClient, use HttpRequestCurlCommandBuilder:
```
CurlGen obj = new CurlGen(new OkhttpRequestCurlCommandBuilder(okHttpRequest));
```

A sample usage to generate a curl command for HttpRequest looks like this:

![alt text](https://i.postimg.cc/P5bztndK/Screen-Shot-2021-05-05-at-5-46-09-PM.png)

The generated cURL command as seen in logs:

![alt_text](https://i.postimg.cc/7ZL0yGQP/Screen-Shot-2021-05-05-at-5-46-32-PM.png)

The generated command is stored in obj and can be used by the user to make command line calls, sharing, etc.

The library can also be used to manually generate the curl command by individually modifying the headers, url and options.

For example:

![alt_text](https://i.postimg.cc/WzDCK1L5/Screen-Shot-2021-05-05-at-6-47-36-PM.png)

Methods exposed to manually generate a curl command:
```
addHeaders(Map<String, String>)
addHeader(String, String)
getHeader(String)
getHeaders()

setUrl(String)
getUrl()

addOptions(Map<Option, String>)
addOption(Option, String)
getOptions()

getCurlCommand()
```
