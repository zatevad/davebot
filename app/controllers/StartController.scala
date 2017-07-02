package controllers

import javax.inject.{Inject, Singleton}

import models.StartRequest
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import service.Analyser

import scala.concurrent.Future

@Singleton
class StartController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def start() = Action.async(parse.json) {
    implicit request =>
      request.body.validate[StartRequest].fold(
        error => {
          println(error)
          Future.successful(BadRequest(utils.JSONFactory.generateErrorJSON(play.api.http.Status.BAD_REQUEST, Left(error))))
        },
        result => {
          println(result)
          Analyser.initialise(result)
          Future.successful(Ok)
        }
      )
  }
}
