package com.marekdudek

import com.marekdudek.Generator2.{booleans, integers, pairs}
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
}
