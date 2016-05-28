package com.marekdudek

import scala.math.abs

object SquareNewtonsWay {

  def sqrt(x: Double): Double = {

    def sqrtIter(guess: Double): Double = {

      def isGoodEnough =
        abs(guess * guess - x) / x < 0.001

      def improve =
        (guess + x / guess) / 2

      if (isGoodEnough)
        guess
      else
        sqrtIter(improve)
    }

    sqrtIter(1.0)
  }
}
