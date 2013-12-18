import sbt._

object ItertoolsBuild extends Build {

  lazy val root = Project("root", file(".")) dependsOn(generator)
  lazy val generator = RootProject(uri("git://github.com/jcrudy/generator.git"))

}