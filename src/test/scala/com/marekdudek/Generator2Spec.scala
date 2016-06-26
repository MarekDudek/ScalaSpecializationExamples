package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class Generator2Spec extends FlatSpec with Matchers {

  private val integers = new Generator2[Int] {

    private val rand = new java.util.Random

    def generate =
      rand.nextInt()
  }

  private val booleans =
    for (x <- integers) yield x > 0

  "boolean generator" should "work" in {
    // when
    val bs =
      for (i <- 1 to 100)
        yield booleans.generate
    // then
    bs should contain(true)
    bs should contain(false)
  }

  private def pairs[T, U](t: Generator2[T], u: Generator2[U]) =
    t.flatMap {
      x => u map { y => (x, y) }
    }

  "pair generator" should "work" in {
    // given
    val intBoolPairs = pairs(integers, booleans)
    // then
    val ps = for (i <- 1 to 100)
      yield intBoolPairs.generate
  }

  private def single[T](x: T): Generator2[T] =
    new Generator2[T] {
      def generate = x
    }

  private def choose(lo: Int, hi: Int): Generator2[Int] =
    for (x <- integers)
      yield lo + (x % (hi - lo))

  private def oneOf[T](xs: T*): Generator2[T] =
    for (idx <- choose(0, xs.length))
      yield xs(idx)
}
