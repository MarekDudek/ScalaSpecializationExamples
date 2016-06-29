package com.marekdudek

import com.marekdudek.Approximations._
import com.marekdudek.FixedPoint.fixedPoint

import scala.annotation.tailrec

object SquareNewtonsWay {

  def sqrt(x: Double): Double = {

    @tailrec
    def sqrIter(guess: Double): Double = {

      def improve =
        average(guess, x / guess)

      if (closeEnough(guess * guess, x)) guess else sqrIter(improve)
    }

    sqrIter(1.0)
  }

  def sqrt2(x: Double): Double =
    fixedPoint(y => average(x / y, y))(1.0)

  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) =
      average(guess, x / guess)
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }
}
