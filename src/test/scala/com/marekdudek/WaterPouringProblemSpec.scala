package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class WaterPouringProblemSpec extends FlatSpec with Matchers {

  "water pouring problem" should "be solved" in {
    // given
    val wpp = new WaterPouringProblem(Vector(4, 9))
    // when
    val solution = wpp.solution(6).head
    // then
    solution shouldBe new wpp.Path(List(
      wpp.Fill(1),
      wpp.Pour(1, 0),
      wpp.Empty(0),
      wpp.Pour(1, 0),
      wpp.Empty(0),
      wpp.Pour(1, 0),
      wpp.Fill(1),
      wpp.Pour(1, 0)
    ).reverse,
      Vector(4, 6).asInstanceOf[WaterPouringProblem#State])
  }
}
