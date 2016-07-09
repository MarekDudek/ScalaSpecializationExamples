package com.marekdudek

import com.marekdudek.Loops._
import org.scalatest.{FlatSpec, Matchers}

import scala.language.reflectiveCalls

class LoopsSpec extends FlatSpec with Matchers {

  "power" should "properly compute" in {
    power(2, 3) shouldBe 8
  }

  "power2" should "properly compute" in {
    power2(2, 3) shouldBe 8
  }

  "repeat" should "work" in {
    // given
    var a = 0
    // when
    repeat {
      a += 1
    }(condition = true)
    // then
    a shouldBe 1

    // given
    var b = 5
    // when
    repeat {
      b -= 1
    }(b == 0)
    // then
    b shouldBe 0
  }

  "repeat until" should "work" in {
    // given
    var a = 5
    // when
    repeat2 {
      a -= 1
    } until (a == 0)
    // then
    a shouldBe 0
  }

  "repeat until (3)" should "work" in {
    // given
    var a = 5
    // when
    repeat3 {
      a -= 1
    } until (a == 0)
    // then
    a shouldBe 0
  }
}
