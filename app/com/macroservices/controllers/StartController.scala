package com.macroservices.controllers

import javax.inject.{Inject, Singleton}

import com.macroservices.models.StartRequest
import com.macroservices.service.Analyser
import com.macroservices.utils.JSONFactory
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

@Singleton
class StartController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def start() = Action.async(parse.json) {
    implicit request =>
      request.body.validate[StartRequest].fold(
        error => {
          println(error)
          Future.successful(BadRequest(JSONFactory.generateErrorJSON(play.api.http.Status.BAD_REQUEST, Left(error))))
        },
        result => {
          println(result)
          Analyser.initialise(result)
          Future.successful(Ok)
        }
      )
  }
}
