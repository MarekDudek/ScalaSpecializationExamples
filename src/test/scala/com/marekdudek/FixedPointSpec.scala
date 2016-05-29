package com.marekdudek

import com.marekdudek.Approximations.tolerance
import com.marekdudek.FixedPoint.fixedPoint
import org.scalatest.{FlatSpec, Matchers}

class FixedPointSpec extends FlatSpec with Matchers {

  "1 + x/2" should "have fixed point close to 2" in {
    val fp = fixedPoint(x => 1 + x/2)(1)
    fp shouldBe 2.0 +- 1e-3
  }
}
