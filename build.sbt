import NativePackagerKeys._

packagerSettings

packageArchetype.java_application

name := "wob"

version := "1.0"

scalaVersion := "2.10.4-RC3"

packageSummary := "wall of blame"

packageDescription := "wob debian package"

maintainer := "Andreas Drobisch <andreas@drobisch.com>"

libraryDependencies += "javazoom" % "jlayer" % "1.0.1"

libraryDependencies += "io.spray" % "spray-client" % "1.2.0"

libraryDependencies += "io.spray" % "spray-routing" % "1.2.0"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.2.3"

libraryDependencies += "io.spray" % "spray-json_2.10" % "1.2.5"

libraryDependencies += "org.fusesource.scalate" % "scalate-core_2.10" % "1.6.1"

addCommandAlias("deb", "debian:package-bin")

seq(Revolver.settings: _*)
