package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class TrySpec extends FlatSpec with Matchers {

  "try" should "allow functional exception handling" in {
    // when
    val byZero = MTry {
      1 / 0
    }
    // then
    byZero shouldBe a[Failure]
  }

  "try" should "be usable in for expressions" in {
    // when
    val failure = for {
      a <- MTry(1 / 0)
      b <- MTry(2 / 3)
    } yield a + b
    // then
    failure shouldBe a[Failure]

    // when
    val sum = for {
      a <- MTry(1.0 / 3)
      b <- MTry(2.0 / 3)
    } yield a + b
    // then
    sum shouldBe Success(1.0)
  }
}
