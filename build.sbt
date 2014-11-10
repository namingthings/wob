import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.SbtNativePackager.packagerSettings
import com.typesafe.sbt.SbtNativePackager.NativePackagerKeys._
import spray.revolver.RevolverPlugin.Revolver

packagerSettings

packageArchetype.java_application

Revolver.settings.settings

name := "wob"

version := "1.0"

scalaVersion := "2.11.4"

packageSummary := "wall of blame"

packageDescription := "wob debian package"

maintainer := "Andreas Drobisch <andreas@drobisch.com>"

libraryDependencies += "javazoom" % "jlayer" % "1.0.1"

libraryDependencies += "com.squareup.retrofit" % "retrofit" % "1.7.1"

libraryDependencies += "commons-codec" % "commons-codec" % "1.8"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3"

libraryDependencies += "io.spray" %% "spray-can" % "1.3.2"

libraryDependencies += "io.spray" %% "spray-routing" % "1.3.2"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.6"

libraryDependencies += "io.spray" %% "spray-json" % "1.3.0"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.10"

libraryDependencies += "org.scalatra.scalate" %% "scalate-core" % "1.7.0"

addCommandAlias("deb", "debian:package-bin")
