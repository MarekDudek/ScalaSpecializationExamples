
name    := "ScalaSpecExamples"
version := "1.0"

scalaVersion := "2.11.8"
scalacOptions ++= Seq("-deprecation", "-explaintypes", "-feature", "-unchecked", "-optimise", "-target:jvm-1.8")

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "2.2.6" withSources() withJavadoc(),
  "org.scalatest" %% "scalatest" % "2.2.6" % "test" withSources() withJavadoc()
)

scalastyleConfig := file("project/scalastyle-config.xml")
