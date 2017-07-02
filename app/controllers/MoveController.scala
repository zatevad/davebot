package controllers

import javax.inject.{Inject, Singleton}

import models.LastMoveRequest
import play.api.data.validation.ValidationError
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsPath, Json}
import play.api.mvc.{Action, Controller}
import service.Analyser

import scala.concurrent.Future

@Singleton
class MoveController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def move() = Action {
    //Determine best item to use
    val bestItem: String = Analyser.getBestGuess
    println(s"controller = ${bestItem}")
    Ok(Json.toJson(bestItem))
  }

  def lastOpponentMove() = Action.async(parse.json) {
    implicit request =>
      request.body.validate[LastMoveRequest].fold(
        (error: Seq[(JsPath, Seq[ValidationError])]) => {
          println(error)
          Future.successful(BadRequest(utils.JSONFactory.generateErrorJSON(play.api.http.Status.BAD_REQUEST, Left(error))))
        },
        (result: LastMoveRequest) => {
          println(result)
          Analyser.saveLastMove(result)
          Future.successful(Ok)
        }
      )
  }
}
