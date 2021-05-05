logLevel := Level.Info

name := "CS540_Bhuvana_Sridhara_Project_CurlGen"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe" % "config" % "1.2.1",
  "com.squareup.okhttp3" % "okhttp" % "4.10.0-RC1",
  "org.apache.httpcomponents" % "httpclient" % "4.5.13",

  // YAML
  "org.junit.platform" % "junit-platform-runner" % "1.0.0-M3" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.0.0-M3" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test

)


