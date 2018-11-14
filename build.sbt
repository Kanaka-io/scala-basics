scalaVersion in ThisBuild := "2.12.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

traceLevel := -1

logLevel := Level.Info

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"

addCommandAlias("partie1-1", "~ testOnly first_steps")

addCommandAlias("partie1-2", "~ testOnly next_steps")

addCommandAlias("partie2", "~ testOnly we_need_to_go_deeper")

addCommandAlias("partie3", "~ testOnly cons_et_nil")

addCommandAlias("partie4", "~ testOnly type_classes")

addCommandAlias("partie5", "~ testOnly un_sac_avec_des_items")

addCommandAlias("partie6", "~ testOnly bonus_event_sourcing")

addCommandAlias("go", "~ testOnly HandsOnScala")
