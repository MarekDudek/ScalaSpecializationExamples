package com.marekdudek

import com.marekdudek.Approximations._

import scala.annotation.tailrec

object SquareNewtonsWay {

  def sqrt(x: Double): Double = {

    @tailrec
    def sqrIter(guess: Double): Double = {

      def improve =
        mean(guess, x / guess)

      if (closeEnough(guess * guess, x)) guess else sqrIter(improve)
    }

    sqrIter(1.0)
  }
}
