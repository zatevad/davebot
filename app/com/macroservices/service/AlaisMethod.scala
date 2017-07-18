package com.macroservices.service

import scala.collection.mutable.{MutableList, Queue}
import scala.util.Random

object Runner extends App {
  val prob = List(0.05, 0.15, 0.35, 0.25, 0.09, 0.1, 0.01)
  val acc = new Array[Int](prob.size)
  val am = AliasMethod(prob)
  for (i <- 0 until 1000000) acc(am.next) += 1
  for (v <- acc) print("%d\t".format(v))
}

class AliasMethod(givenProbs: List[Double], random: Random) {
  // copy data into a mutable list
  val probabilities = MutableList[Double](givenProbs: _*)
  // declare probability and alias tables
  val prob = new Array[Double](probabilities.size)
  val alias = new Array[Int](probabilities.size)
  // declare the working queues
  val small, large = new Queue[Int]
  // compute the average
  val average: Double = 1.0 / probabilities.size;

  // populate the working queues
  for (p <- probabilities.indices)
    if (probabilities(p) >= average) large += p else small += p
  // use both queues as sentinel value
  while (small.size != 0 && large.size != 0) {
    val less = small.dequeue
    val more = large.dequeue
    // assign and scale probability
    prob(less) = probabilities(less) * probabilities.size
    alias(less) = more
    // decrease the probability of the larger one
    probabilities(more) = (probabilities(more) + probabilities(less)) - average
    // assign to large or small queue
    if (probabilities(more) >= average) large += more else small += more
  }
  // the remaining probabilities are now 1/n so we empty the queues
  for (i <- large) prob(i) = 1.0
  for (i <- small) prob(i) = 1.0

  // sample the next value from given distribution
  def next: Int = {
    val column = random.nextInt(prob.size)
    val coinToss = random.nextDouble() < prob(column)
    if (coinToss) column else alias(column)
  }
}

object AliasMethod {
  def apply(probs: List[Double], random: Random = new Random) = new AliasMethod(probs, random)
}
