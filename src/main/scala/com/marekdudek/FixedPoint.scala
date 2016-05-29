package com.marekdudek

import com.marekdudek.Approximations._

import scala.annotation.tailrec

object FixedPoint {

  def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {

    @tailrec
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (closeEnough(guess, next)) next else iterate(next)
    }

    iterate(firstGuess)
  }
}
