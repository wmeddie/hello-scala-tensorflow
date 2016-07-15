name := "hello-scala-tensorflow"

version := "1.0"

scalaVersion := "2.11.8"

classpathTypes += "maven-plugin"

javaCppVersion := "1.2"

javaCppPresetLibs ++= Seq("tensorflow" -> "0.8.0")

mainClass in Compile := Some("com.yumusoft.tf.HelloTensorflow")
