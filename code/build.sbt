organization := "underscore.io"

name := "scala-macros"

version := "1.0.0"

val commonSettings = Seq(
  scalaVersion := "2.11.0",
  scalacOptions += "-deprecation",
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.specs2" % "specs2_2.11.0-RC1" % "2.3.10" % "test",
    "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4"
  )
)

lazy val helloLib = project.in(file("hello/lib"))
  .settings(commonSettings : _*)

lazy val helloApp = project.in(file("hello/app"))
  .dependsOn(helloLib)
  .settings(commonSettings : _*)

lazy val desugarLib = project.in(file("desugar/lib"))
  .settings(commonSettings : _*)

lazy val desugarApp = project.in(file("desugar/app"))
  .dependsOn(desugarLib)
  .settings(commonSettings : _*)

lazy val root = project.in(file("."))
  .aggregate(
    helloLib,
    helloApp,
    desugarLib,
    desugarApp
  )
