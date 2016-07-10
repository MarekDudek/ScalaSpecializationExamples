package com.marekdudek.scalacheck

import org.scalacheck.Prop.{BooleanOperators, atLeastOne, forAll}
import org.scalacheck.{Gen, Prop}
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import scala.math.sqrt

class ScalaCheckSpec extends FunSuite with Checkers {

  val propConcatLists: Prop = forAll {
    (l1: List[Int], l2: List[Int]) =>
      l1.size + l2.size == (l1 ::: l2).size
  }
  test("my first property should check") {
    check(propConcatLists)
  }

  val smallInts = Gen.choose(0, 10000)
  val propSqrt: Prop = forAll(smallInts) {
    n: Int =>
        sqrt(n * n) == n
  }
  test("my second property should also check") {
    check(propSqrt)
  }

  val propReverseList = forAll {
    list: List[String] =>
      list.reverse.reverse == list
  }
  test("list reversal property") {
    check(propReverseList)
  }

  val propConcatString = forAll {
    (s1: String, s2: String) =>
      (s1 + s2) endsWith s2
  }
  test("string concatenation") {
    check(propConcatString)
  }

  val smallInteger = Gen.choose(0, 100)
  val propSmallInteger = forAll(smallInteger) {
    n =>
      n >= 0 && n <= 100
  }
  test("small integers") {
    check(propSmallInteger)
  }

  val closeToZero = Gen.choose(-10000, 10000)
  val propMakeList = forAll(closeToZero) {
    n: Int =>
      (n >= 0) ==>
        (List.fill(n)(" ").length == n)
  }
  test("conditional properties") {
    check(propMakeList)
  }

  val nonNegative = forAll(smallInts) {
    n: Int =>
      n >= 0
  }
  val negative = forAll(smallInts) {
    n: Int =>
      n < 0
  }
  val everyInteger = atLeastOne(negative, nonNegative)
  test("always holds") {
    check(everyInteger)
  }
}
