name := "postgres.test"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

logBuffered in Test := false

scalacOptions ++= Seq("-unchecked", "-deprecation")

//resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  // this kills the db interaction
  //"com.zaxxer" % "HikariCP" % "2.4.1",  //@TODO: still need this??
  "org.scalactic" %% "scalactic" % "3.0.4",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)