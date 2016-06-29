package com.marekdudek

object Primes {

  def isPrime(n: Int): Boolean =
    !((2 until n - 1) exists (n % _ == 0))

  def from(n: Int): Stream[Int] =
    n #:: from(n + 1)

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

  def primes: Stream[Int] =
    sieve(from(2))
}
