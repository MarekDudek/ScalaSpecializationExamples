package com.marekdudek

import scala.math._

object Approximations {

  val tolerance = 0.0001

  def distance(a: Double, b: Double): Double =
    abs(a - b)

  def relativeDistance(a: Double, b: Double): Double =
    distance(a, b) / b

  def closeEnough(a: Double, b: Double): Boolean =
    relativeDistance(a, b) < tolerance

  def mean(a: Double, b: Double): Double =
    (a + b) / 2

}
