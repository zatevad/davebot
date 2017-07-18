package com.macroservices.utils

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import com.macroservices.service.GameRules.{Item, Tie, Win}
import com.macroservices.service.{Game, RPSDW}

import scala.concurrent.{ExecutionContext, Future}


object Initialiser {
  /**
    * Start a game of RPSDW
    *
    * @param userOption The user's selected Item
    */
  def startGame(userOption: Option[Item])(implicit system: ActorSystem): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val timeout = Timeout(500, TimeUnit.MILLISECONDS)

    val game = system.actorOf(Game.props(RPSDW))

    val result = userOption match {
      case None =>
        val player1Item = RPSDW.randomItem
        val player2Item = RPSDW.randomItem
        play(game, "Player 1", "Player 2", player1Item, player2Item)

      case Some(userItem) =>
        val computerItem = RPSDW.randomItem
        play(game, "You", "The computer", userItem, computerItem)
    }

    result foreach { r =>
      println(r)
      system.terminate()
    }
  }

  /**
    * Play a given game. This is a Façade on top of the Game actor. Architecturally it would be part of the View, but for
    * the simple command-line version this is a convenient place to put it.
    *
    * @param game        The instance of the Game to play
    * @param player1Name The name of the first player (for return message purposes)
    * @param player2Name The name of the second player (for result message purposes)
    * @param player1Item The Item selected by the first player
    * @param player2Item The Item selected by the second player
    * @param ec          An ExecutionContext to execute an Ask command
    * @param timeout     A Timeout for getting a message back to the Actor
    * @return A Future containing a message with the result
    */
  private def play(game: ActorRef,
                   player1Name: String,
                   player2Name: String,
                   player1Item: Item,
                   player2Item: Item)(implicit ec: ExecutionContext, timeout: Timeout): Future[String] = {
    game ! (player1Item)

    val prefix = s"$player1Name selected $player1Item\n$player2Name selected $player2Item\n"

    (game ? player2Item) map({
          case Tie =>
            s"$prefix$player1Name tied with $player2Name"

          case Win(`player1Item`, name, p2Item) ⇒
            s"$prefix$player1Name won! $player1Item $name $player2Item"

          case Win(cItem, name, _) =>
            s"$prefix$player2Name won! $player2Item $name $player1Item"
        })
  }
}
