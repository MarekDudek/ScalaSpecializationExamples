package com.marekdudek

import com.marekdudek.Generator2.{booleans, integers, lists, pairs}
import org.scalatest.{FlatSpec, Matchers}

class Generator2Spec extends FlatSpec with Matchers {

  "boolean generator" should "work" in {
    // when
    val bs =
      for (i <- 1 to 100)
        yield booleans.generate
    // then
    bs should contain(true)
    bs should contain(false)
  }

  "pair generator" should "work" in {
    // given
    val intBoolPairs = pairs(integers, booleans)
    // then
    val ps = for (i <- 1 to 100)
      yield intBoolPairs.generate
  }

  def test[T](g: Generator2[T], numTimes: Int = 100)(test: T => Boolean): Unit =
    for (i <- 0 until numTimes) {
      val value = g.generate
      assert(test(value), "test failed for " + value)
    }

  "pairs" should "be tested with randomised examples" in {

    test(pairs(lists, lists)) {
      case (xs, ys) =>
        (xs ++ ys).length >= xs.length
    }
  }
}
