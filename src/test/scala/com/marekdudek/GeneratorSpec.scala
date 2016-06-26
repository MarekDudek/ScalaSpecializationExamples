package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class GeneratorSpec extends FlatSpec with Matchers {

  private val integers = new Generator[Int] {

    private val rand = new java.util.Random

    def generate =
      rand.nextInt()
  }

  private val booleans = new Generator[Boolean] {
    def generate =
      integers.generate > 0
  }

  private val pairs = new Generator[(Int, Int)] {
    def generate =
      (integers.generate, integers.generate)
  }
}
