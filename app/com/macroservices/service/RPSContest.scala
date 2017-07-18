package com.macroservices.service

import java.io.{File, FileNotFoundException}
import java.util.{Random, Scanner}


class rpsAI() { //constructor for the AI class
//  private var markovChain = Array[Array[Float]](Array(0.33f, 0.33f, 0.33f), Array(0.33f, 0.33f, 0.33f), Array(0.33f, 0.33f, 0.33f)) //Didn't really see a need to do this via a loop when a simple assign statement would do
//
//  private var timesPlayed = Array[Int](0, 0, 0)
////  private var markovChain = null // used to store markov chain probabilities
////
////  private var timesPlayed = null // contains the number of times the player has played each of the three moves. For calculation purposes.
//
//  private var lastMove = 0 //last move of the human player
//
//  private var moveBeforeLast = 0 //move before last of the human player
//
//  def makeMove: String = { //main function for the class, probabilistically guesses the player's next move, then counters it
//    val rand = new Random
//    //establish a random number generator
//    val ranFloat = rand.nextFloat //generate a random float
//    if (ranFloat <= markovChain(lastMove)(1)) { //use that random float, along with our Markov Chain values of potential moves, to generate a probabilisically determined move
//      "paper" //Note that the returned moves are, in fact, the counters to the move the AI predicts the user to perform
//
//    }
//    else if (ranFloat <= markovChain(lastMove)(2) + markovChain(lastMove)(1)) { //We add these two values together here to properly model the possibility space
//      "scissors"
//    }
//    else "rock"
//  }
//
//  def update(newMove: String): Unit = { //Takes in the results of the last game played, and uses them to update the Markov chain using relevant data
//    moveBeforeLast = lastMove
//    if (newMove == "rock") lastMove = 0
//    else if (newMove == "paper") lastMove = 1
//    else lastMove = 2
//    //Here comes the hard part: updating the Markov Chain
//    /*
//         * 1. Multiply everything in the appropriate column of the Markov Chain by timesPlayed[moveBeforeLast]
//         * 2. Increment the row value we want (that is, markovChain[moveBeforeLast][lastMove] by one
//         * 3. Increment timesPlayed[moveBeforeLast] by one
//         * 4. Divide all values in markovChain[moveBeforeLast][x] by timesPlayed[moveBeforeLast]
//         */ var i = 0
//    while ( {
//      i < 3
//    }) { //1. Multiply everything in the appropriate column of the Markov Chain by timesPlayed[moveBeforeLast]
//      markovChain(moveBeforeLast)(i) *= timesPlayed(moveBeforeLast)
//
//      {
//        i += 1; i - 1
//      }
//    }
//    //2. Increment the row value we want (that is, markovChain[moveBeforeLast][lastMove] by one
//    markovChain(moveBeforeLast)(lastMove) += 1
//    //3. Increment timesPlayed[moveBeforeLast] by one
//    timesPlayed(moveBeforeLast) += 1
//    //4. Divide all values in markovChain[moveBeforeLast][x] by timesPlayed[moveBeforeLast]
//    var j = 0
//    while ( {
//      j < 3
//    }) {
//      markovChain(moveBeforeLast)(j) /= timesPlayed(moveBeforeLast)
//
//      {
//        j += 1; j - 1
//      }
//    }
//    //For debug purposes, let's go ahead and print the contents of this Markov Chain:
//    System.out.println("New Markov Chain")
//    System.out.println("Rock to Rock: " + markovChain(0)(0) + " Rock to Paper: " + markovChain(0)(1) + " Rock to Scissors: " + markovChain(0)(2))
//    System.out.println("Paper to Rock: " + markovChain(1)(0) + " Paper to Paper: " + markovChain(1)(1) + " Paper to Scissors: " + markovChain(1)(2))
//    System.out.println("Scissors to Rock: " + markovChain(2)(0) + " Scissors to Paper: " + markovChain(2)(1) + " Scissors to Scissors: " + markovChain(2)(2))
//  }
//
//  def saveData(keyboard: Scanner): Unit = { //Function which saves the Markov chain developed by the AI to a text file for later use
////    System.out.print("Enter the name of the file: ")
////    val filename = keyboard.nextLine
////    //User inputs their desired name for the file
////    val currentRelativePath = Paths.get("")
////    //Current working directory for the program is found
////    val workingDirectory = currentRelativePath.toAbsolutePath.toString
////    //This directory is then converted into an absolute path, then into a string
////    val newFilePath = workingDirectory + "/" + filename
////    //this string then has the new file appended to the end (TODO: I think I fucked this up? Is this pointing to a file or directory?)
////    val outfile = new File(newFilePath)
////    //then creates a new file at the address
////    var output = null //PrintWriter is then opened
////    try {
////      output = new PrintWriter(outfile) //Printwriter is connected to the outfile
////
////      var i = 0
////      while ( {
////        i < 3
////      }) { //and writes the contents of the Markov Chain to it
////        var j = 0
////        while ( {
////          j < 3
////        }) {
////          output.print(markovChain(i)(j))
////          output.print(" ")
////
////          {
////            j += 1; j - 1
////          }
////        }
////
////        {
////          i += 1; i - 1
////        }
////      }
////      var k = 0
////      while ( {
////        k < 3
////      }) { //and we also print the contents of timesPlayed to it as well, so our future loaded AI can properly update its inherited Markov Chain
////        output.print(timesPlayed(k))
////        output.print(" ")
////
////        {
////          k += 1; k - 1
////        }
////      }
////      output.close()
////    } catch {
////      case e: FileNotFoundException =>
////        //I don't think this will ever trigger, but in case it does, this exception is caught
////        System.out.println("That file was not found.")
////    }
//  }
//
//  def loadData(keyboard: Scanner): Unit = { //A function which reads in a file and reconstructs the described Markov Chain
//    System.out.print("Please enter the name of the file, including file extension:")
//    val file = keyboard.nextLine //User enters in the name of the data file
//    try {
//      val input = new Scanner(new File(file))
//      //A scanner is then opened to read in the data
//      var i = 0
//      while ( {
//        i < 3
//      }) { //Use the setter for the RPSAI to construct the old Markov Chain
//        var j = 0
//        while ( {
//          j < 3
//        }) {
//          this.setChain(i, j, input.nextFloat)
//
//          {
//            j += 1; j - 1
//          }
//        }
//
//        {
//          i += 1; i - 1
//        }
//      }
//      var k = 0
//      while ( {
//        k < 3
//      }) { //and also reconstruct our old timesPlayed array
//        this.setTimesPlayed(k, input.nextInt)
//
//        {
//          k += 1; k - 1
//        }
//      }
//      input.close()
//    } catch {
//      case e: FileNotFoundException =>
//        //if we can't find the file the user's talking about, let 'em know, and exit the function
//        System.out.println("That file was not found.")
//    }
//  }
//
//  private def setChain(x: Int, y: Int, probability: Float) = { //simple setter routine, used to generate a Markov Chain from a file
//    markovChain(x)(y) = probability
//  }
//
//  private def setTimesPlayed(x: Int, y: Int) = {
//    timesPlayed(x) = y
//  }
//}
//
//object game {
//  def main(args: Array[String]): Unit = {
//    var wannaQuit = false
//    //wannaQuit serves as the boolean operator for the main do-while loop
//    val opponent = new rpsAI
//    //create a new AI
//    val keyboard = new Scanner(System.in) //then open up a Scanner to read user input. This'll get used a /lot/
//    do {
//      System.out.println("Welcome to the Rock, Paper, Scissors AI Challenge!")
//      System.out.println("Please select a numeric option below:")
//      System.out.println("1. Play RPS against a new AI")
//      System.out.println("2. Load the probabilistic data from an old session")
//      System.out.println("3. Quit")
//      System.out.print("Please enter a choice: ") //Print out options. Standard affair.
//
//      var choice = keyboard.nextLine.toInt //read the user's input, then try to parse an int from it, representing the three menu options
//      while ( {
//        choice > 3 || choice < 1
//      }) { //If the int you get isn't a valid input given the menu
//        System.out.println("Invalid entry.") //Waggle your finger at the user
//
//        System.out.print("Please enter a valid choice: ") //Then make them enter a new option
//
//        keyboard.nextLine
//        choice = keyboard.nextLine.toInt
//      }
//      choice match { //Now that we've verified input, this switch sends the user to the proper function given their request.
//        case 1 =>
//          play(keyboard, opponent)
//          //break //todo: break is not supported
//        case 2 =>
//          opponent.loadData(keyboard)
//          //break //todo: break is not supported
//        case 3 =>
//          wannaQuit = true //Except case 3, of course, which simply breaks the do-while loop
//
//          //break //todo: break is not supported
//        case _ =>
//          //break //todo: break is not supported
//      }
//    } while ( {
//      !wannaQuit
//    })
//  }
//
//  def play(keyboard: Scanner, opponent: rpsAI): Unit = { //play() forms the core gameplay loop of the game
//    System.out.println("Let's play Rock, Paper, Scissors!")
//    var playing = true
//    do { //This do-while loop is, literally, the gameplay loop
//      System.out.print("Enter 'rock', 'paper' or 'scissors':")
//      var playermove = keyboard.nextLine.trim.toLowerCase //prompt the user for a move, then correct it to proper style
//      while ( {
//        playermove == "rock" && playermove == "paper" && playermove == "scissors"
//      }) { //if the user's input isn't rock, paper, or scissors
//        System.out.println("That is not a valid move!") //Scold 'em
//
//        System.out.print("Please enter 'rock', 'paper', or 'scissors'") //Then make them re-enter
//
//        playermove = keyboard.nextLine.trim.toLowerCase
//      }
//      val AIMove = opponent.makeMove
//      //Now that we have the user's move, have the AI generate it's move
//      val result = results(playermove, AIMove) //Then send both moves to results() to generate the results
//      if (result == 0) { //then, given the result of results, print out a message describing the outcome of the game
//        System.out.println("It was a tie! Both of you played " + playermove)
//      }
//      else if (result == 1) System.out.println("You won! " + playermove + " beat " + AIMove)
//      else System.out.println("You lost! " + AIMove + " beat " + playermove)
//      opponent.update(playermove) //then update the AI with the outcome of that game
//
//      System.out.println("What would you like to do?") //Now, offer the user options as to what they want to do next
//
//      System.out.println("1. Keep playing")
//      System.out.println("2. Save AI data to file, and keep playing")
//      System.out.println("3. Return to menu")
//      System.out.print("Please select an option: ")
//      var choice = keyboard.nextLine.toInt //Have them enter a numeric choice from the menu, then correct it as needed
//      while ( {
//        choice > 3 || choice < 1
//      }) {
//        System.out.println("Invalid entry.")
//        System.out.print("Please enter a valid choice: ")
//        keyboard.nextLine
//        choice = keyboard.nextLine.toInt
//      }
//      choice match {
//        case 1 => //Case 1 simply has them traverse this do-while loop again
//
//          //break //todo: break is not supported
//        case 2 =>
//          opponent.saveData(keyboard) //Case 2 tells the opponent to begin the process of saving it's knowledge to a file
//
//          System.out.println("Do you wanna keep playing?:")
//          System.out.println("1. Yes")
//          System.out.println("2. No")
//          choice = keyboard.nextLine.toInt
//          while ( {
//            choice != 1 && choice != 2
//          }) {
//            System.out.println("Invalid entry.")
//            System.out.print("Please enter a valid choice: ")
//            keyboard.nextLine
//            choice = keyboard.nextLine.toInt
//          }
//          if (choice == 2) playing = false
//          //break //todo: break is not supported
//        case 3 =>
//          playing = false //And Case 3 breaks this do-while loop, allowing the user to return to the main menu
//
//          //break //todo: break is not supported
//        case _ =>
//          //break //todo: break is not supported
//      }
//    } while ( {
//      playing
//    })
//  }
//
//  def results(playerMove: String, AIMove: String): Int = { //Results is an extremely simple function which returns the results of a game of rock, paper, scissors
//    if (playerMove == "rock") if (AIMove == "rock") 0 //a return of 0 indicates a tie
//    else if (AIMove == "paper") -1 //a return of -1 indicates a player 2 win (which in this case is the AI)
//    else 1 //and a return of 1 indicates a player 1 win (which in this case is the player)
//    else if (playerMove == "paper") if (AIMove == "rock") 1
//    else if (AIMove == "paper") 0
//    else -1
//    else if (AIMove == "rock") -1
//    else if (AIMove == "paper") 1
//    else 0
//  }
}