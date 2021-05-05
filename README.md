# CurlGen
Automatic cURLcommand generator library

### Team: 
* Bhuvana Sridhara bsridh5@uic.edu
* Pramodh Acharya pachar7@uic.edu
* Prajwal Kammardi pkamma2@uic.edu

### Contents

* [Requirements](#markdown-header-requirements)
* [Building the code](#markdown-header-building-the-code) 
* [Description](#markdown-header-description)
* [Components](#markdown-header-components)
	* [HttpRequest](#markdown-header-query-builder)
	* [HttpUriRequest](#markdown-header-http-client)
	* [OkHttpClient](#markdown-header-scala-models)
* [Usage](#markdown-header-usage)
* [Class reference](#markdown-header-class-reference)
* [Areas of improvement](#markdown-header-areas-of-improvement)

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

The library offers support for the following :

HttpRequest (https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html)
HttpUriRequest (https://javadoc.io/doc/org.apache.httpcomponents/httpclient/4.5.1/org/apache/http/client/methods/HttpUriRequest.html)
OkHttpClient (https://javadoc.io/doc/com.squareup.okhttp3/okhttp/3.3.1/okhttp3/OkHttpClient.html)

