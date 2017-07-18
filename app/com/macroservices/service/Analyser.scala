package com.macroservices.service

import com.macroservices.models.{LastMoveRequest, StartRequest}

object Analyser {
  def initialise(result: StartRequest): Unit = {

  }

  def saveLastMove(result: LastMoveRequest): Unit = {

  }

  def getBestGuess: String = {
    val decision = RPSDW.randomWeapon
    println(s"Analyser = ${decision}")
    decision
  }

}
