package com.marekdudek

import com.marekdudek.Primes.{from, isPrime, primes}
import org.scalatest.{FlatSpec, Matchers}

class StreamsSpec extends FlatSpec with Matchers {

  "finding second prime in range" should "be easy" in {
    // given
    val range: Range = 1000 to 10000
    // when
    val secondPrime = (range filter isPrime) (1)
    // then
    secondPrime shouldBe 1013
  }

  "infinite streams" should "be possible to define and handle" in {
    // given
    val nats = from(1)
    // when
    val multiplesOf4 = nats map (_ * 4)
    // then
    multiplesOf4.take(5) shouldBe Stream(4, 8, 12, 16, 20)
  }

  "sieve of Eratoshenes" should "be straightforward to implement" in {
    // then
    primes.take(10) shouldBe Stream(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
  }
}
