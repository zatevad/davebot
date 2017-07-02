package models

import java.sql.Timestamp

import org.joda.time.DateTime

/**
  * Created by davew on 02/07/2017.
  */
case class Battle(id:Int, opponentName: String, opponentWeapon: String, myWeapon: String, result: String, created:DateTime) {

}
