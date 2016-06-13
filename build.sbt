lazy val root = (project in file(".")).
  settings(
    name := "MongoUnit",
    version := "0.1.0",
    scalaVersion := "2.11.8",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      "Atlassian Releases" at "https://maven.atlassian.com/public/",
      "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
    ),
    unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test"),
    libraryDependencies ++= Seq(
      "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.50.3",
      "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0",
      "org.scalatest" %% "scalatest" % "2.2.6"
    ),
    scalacOptions ++= Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xfatal-warnings", // Fail the compilation if there are any warnings.
      "-Xlint", // Enable recommended additional warnings.
      "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
      "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
      "-encoding", "UTF-8",
      "-Xexperimental" // Add experimental scala feature
    )
  )
