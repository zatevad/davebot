
import akka.actor.ActorSystem
import com.macroservices.service.RPSDW
import com.macroservices.utils.Initialiser


object Main {
  def main(args: Array[String]): Unit = {
    // $COVERAGE-OFF$ Disabling coverage for main method since it is a simple test driver
    if (args.length == 0 || args.length > 2) {
      println(
        """
          |Hello Human. Welcome to Rock-Paper-Scissors!
          |
          |If you want to run a computer vs computer game, just pass "computer" as a command-line argument
          |If you want to play against the computer, pass in your selection (rock, paper or scissors) as a
          |command-line argument
          |
          |For example:
          |
          |$ java -jar rock_paper_scissors.jar computer
          |$ java -jar rock_paper_scissors.jar paper
          |
          |You can also run the game as a REST service. To do this, pass "web" as a command-line argument:
          |
          |$ java -jar rock_paper_scissors.jar web
          |
          |The service will run on port 8080.
          |
          |Enjoy!
        """.stripMargin)
    } else {

      implicit val system = ActorSystem("davebot")

      val gameDefinition = RPSDW

      val userItemArgument = args(0).toLowerCase
      val userOption = gameDefinition.nameToItem.get(userItemArgument)

      if (userOption.isEmpty && userItemArgument != "computer") {
        println(s"Illegal item. Valid items for this game are: ${gameDefinition.nameToItem.keys.mkString(", ")}")
        system.terminate()
        System.exit(0)
      }
      // $COVERAGE-ON

      Initialiser.startGame(userOption)(system)

    }
  }



}