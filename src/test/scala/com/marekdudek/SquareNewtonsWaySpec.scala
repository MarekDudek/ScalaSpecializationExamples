package com.marekdudek

import com.marekdudek.SquareNewtonsWay._
import org.scalatest.{FlatSpec, Matchers}

class SquareNewtonsWaySpec extends FlatSpec with Matchers {

  "sqrt" should "give good approximation" in {
    sqrt(2) shouldBe 1.4142 +- 0.0001
  }

  "sqrt" should "work for small numbers" in {
    sqrt(1e-6) shouldBe 1e-3 +- 1e-9
  }

  "sqrt" should "work for large numbers" in {
    sqrt(1e60) shouldBe 1e30 +- 1e26
  }

  "sqrt2" should "give good approximation" in {
    sqrt2(2) shouldBe 1.4142 +- 0.0001
  }

  "sqrt2" should "work for small numbers" in {
    sqrt2(1e-6) shouldBe 1e-3 +- 1e-9
  }

  "sqrt2" should "work for large numbers" in {
    sqrt2(1e60) shouldBe 1e30 +- 1e26
  }
}
