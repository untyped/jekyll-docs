organization := "underscore.io"

name := "scala-macros"

version := "1.0.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.0",

  scalacOptions += "-deprecation",

  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.specs2" % "specs2_2.11.0-RC1" % "2.3.10" % "test"
  )
)

lazy val hello = project
  .in(file("intro/hello"))
  .settings(commonSettings : _*)

lazy val root = project
  .in(file("."))
  .aggregate(
    hello
  )
