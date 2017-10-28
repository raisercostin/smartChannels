import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.raisercostin" %% "jedi-io" % "0.55",
    libraryDependencies += "junit" % "junit" % "4.12" % Test,
    libraryDependencies += "com.univocity" % "univocity-parsers" % "2.5.8",
    resolvers += "raisercostin resolver" at "http://dl.bintray.com/raisercostin/maven"
  )
