package com.macroservices.controllers.server

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.macroservices.service.Analyser
import com.macroservices.service.RPSDW.Rock
import com.macroservices.utils.{Initialiser, JSONFactory}
import play.api.data.validation.ValidationError
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsPath, Json}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

@Singleton
class GameController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  implicit val system = ActorSystem("davebot")

  def move() = Action.async(parse.json) {
    implicit request =>
    request.body.validate[String].fold(
      (error: Seq[(JsPath, Seq[ValidationError])]) => {
        println(error)
        Future.successful(BadRequest(JSONFactory.generateErrorJSON(play.api.http.Status.BAD_REQUEST, Left(error))))
      },
      (result: String) => {
        println(result)
        Initialiser.startGame(Some(Rock))(system)
        Future.successful(Ok)
      }
    )
  }

  def lastOpponentMove() = Action {

    val bestItem: String = Analyser.getBestGuess
    println(s"Server Controller = ${bestItem}")
    Ok(Json.toJson(bestItem))
  }

  def start() = Action {
    val start =
      """
  |    {
  |      "opponentName": "computer",
  |      "pointsToWin": "100",
  |      "maxRounds": "200",
  |      "dynamiteCount": "10"
  |    }
    """
    Ok(Json.toJson(start))
  }
}
