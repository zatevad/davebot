package service

import javax.inject.Inject

import models.Battle
import org.joda.time.DateTime
import play.api.db._

import scala.collection.mutable.ListBuffer


class Storage @Inject()(db: Database) {

  def storeBattle(battle: Battle) = {
    db.withConnection { conn =>
      val stmt = conn.createStatement

      stmt.executeUpdate("INSERT INTO Battles VALUES ("
        + "opponentName = " + battle.opponentName + ","
        + "opponentWeapon = " + battle.opponentWeapon + ","
        + "myWeapon = " + battle.myWeapon + ","
        + "result = " + battle.result +
        ")")
    }
  }

  def retrieveBattlesByOpponent(opponentName: String): List[Battle] = {

    val battles = ListBuffer[Battle]()
    db.withConnection { conn =>
      val stmt = conn.createStatement

      val rs = stmt.executeQuery("SELECT * FROM Battles WHERE opponentName = " + opponentName)

      while (rs.next) {
        val id = rs.getInt("id")
        val opp = rs.getString("opponentName")
        val ow = rs.getString("opponentWeapon")
        val mw = rs.getString("myWeapon")
        val res = rs.getString("result")
        val ts = new DateTime(rs.getTimestamp("created"))

        val battle = Battle(id, opp, ow, mw, res, ts)
        battles += battle
      }
    }
    battles.toList
  }

  def retrieveAllBattles(): List[Battle] = {
    val battles = ListBuffer[Battle]()

    db.withConnection { conn =>
      val stmt = conn.createStatement

      val rs = stmt.executeQuery("SELECT * FROM Battles WHERE opponentName = ")

      while (rs.next) {
        val id = rs.getInt("id")
        val opp = rs.getString("opponentName")
        val ow = rs.getString("opponentWeapon")
        val mw = rs.getString("myWeapon")
        val res = rs.getString("result")
        val ts = new DateTime(rs.getTimestamp("created"))

        val battle = Battle(id, opp, ow, mw, res, ts)
        battles += battle
      }
    }
    battles.toList
  }

  def createTable() = {
    db.withConnection { conn =>
      val stmt = conn.createStatement

      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Battles" +
        " (id integer PRIMARY KEY DEFAULT nextval('serial'), " +
        "opponentName varchar(40) NOT NULL, " +
        "opponentWeapon varchar(10)" +
        "myWeapon varchar(10)" +
        "result varchar(10)" +
        "created timestamp DEFAULT NOW())")
    }
  }
}