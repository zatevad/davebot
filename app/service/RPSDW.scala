package service

import service.GameRules.Item

trait RPSDW extends GameRules {
  override val nameToItem: Map[String, Item] = Map(
    "ROCK" -> Rock,
    "PAPER" -> Paper,
    "SCISSORS" -> Scissors,
    "DYNAMITE" -> Dynamite,
    "WATERBOMB" -> WaterBomb)

  case object Rock extends Item

  case object Paper extends Item

  case object Scissors extends Item

  case object Dynamite extends Item

  case object WaterBomb extends Item

  beats(Rock, "breaks", Scissors)
  beats(Rock, "breaks", WaterBomb)
  beats(Paper, "wraps", Rock)
  beats(Paper, "wraps", WaterBomb)
  beats(Scissors, "cuts", Paper)
  beats(Scissors, "cuts", WaterBomb)
  beats(Dynamite, "destroys", Rock)
  beats(Dynamite, "destroys", Paper)
  beats(Dynamite, "destroys", Scissors)
  beats(WaterBomb, "engulfs", Dynamite)
}

object RPSDW extends RPSDW {
}
