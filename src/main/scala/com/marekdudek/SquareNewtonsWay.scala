package com.marekdudek

import scala.annotation.tailrec
import scala.math.abs

object SquareNewtonsWay {

  def sqrt(x: Double): Double = {

    @tailrec
    def sqrIter(guess: Double): Double = {

      def isGoodEnough = {
        val epsilon = 0.001
        def distance(a: Double, b: Double) =
          abs(a - b)

        val relativeDistance = distance(guess * guess, x) / x
        relativeDistance < epsilon
      }

      def improve = {
        def mean(a: Double, b: Double) =
          (a + b) / 2

        mean(guess, x / guess)
      }

      if (isGoodEnough) guess else sqrIter(improve)
    }

    sqrIter(1.0)
  }
}
