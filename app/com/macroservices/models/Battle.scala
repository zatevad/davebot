package com.macroservices.models

import org.joda.time.DateTime

case class Battle(id: Int, opponentName: String, opponentWeapon: String, myWeapon: String, result: String, created: DateTime) {

}
