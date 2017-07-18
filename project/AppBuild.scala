import sbt._

object AppBuild extends Build with MicroService {

  val appName = "davebot"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies{
  import play.core.PlayVersion
  import play.sbt.PlayImport._

  private val scalaTestVersion = "2.2.6"
  private val scalaTestPlusVersion = "1.5.1"
  private val mockitoVersion = "1.9.5"
  private val wireMockVersion = "1.57"

  val compile = Seq(
    ws,
    "com.github.tomakehurst" % "wiremock" % wireMockVersion % "test,it",
    "com.github.fge" % "json-schema-validator" % "2.2.6"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.mockito" % "mockito-core" % mockitoVersion % scope,
        "org.pegdown" % "pegdown" % "1.5.0" % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlusVersion % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "org.scalatest" %% "scalatest" % "2.2.6" % scope,
        "org.pegdown" % "pegdown" % "1.5.0" % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}