package service

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import service.GameRules.Item

class Game(gameRules: GameRules) extends Actor with ActorLogging {

  /**
    * When we receive the first message, store the first Item and Sender here
    */
  var first: Option[(Item, ActorRef)] = None

  override def receive = {
    case item: Item ⇒
      first match {
        case None ⇒
          first = Some((item, sender))

        case Some((firstItem, firstSender)) ⇒
          val response = gameRules.check(firstItem, item)
          firstSender ! response
          sender ! response
          context.stop(self)
      }
  }
}

/**
  * The companion object of the Game actor
  */
object Game {
  def props(gameRules: GameRules) = Props(classOf[Game], gameRules)
}
