logLevel := Level.Info


lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.cs540.curlgen",
      scalaVersion := "2.13.3"
    )),
    name := "CS540_Bhuvana_Sridhara_Project_CurlGen",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe" % "config" % "1.2.1",
      "com.squareup.okhttp3" % "okhttp" % "4.10.0-RC1",
      "org.apache.httpcomponents" % "httpclient" % "4.5.13",

      // YAML
      "org.junit.jupiter" % "junit-jupiter-api" % "5.6.2" % Test,
      "com.novocode" % "junit-interface" % "0.11" % Test exclude("junit", "junit-dep")

    )
  )


