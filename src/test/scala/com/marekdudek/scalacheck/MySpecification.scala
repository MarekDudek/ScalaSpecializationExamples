package com.marekdudek.scalacheck

import org.scalacheck.Prop.{BooleanOperators, forAll}
import org.scalacheck._

object MySpecification extends Properties("my properties") {

  property("cons lists") = forAll {
    (l1: List[Int], l2: List[Int]) =>
      l1.size + l2.size == (l1 ::: l2).size
  }

  val nonNegative = forAll {
    n: Int =>
      n >= 0
  }
  val negative = forAll {
    n: Int =>
      n < 0
  }

  /*property("every integer") =
    ateLeastOne(nonNegative, negative)*/

  def myMagicFunction(n: Int, m: Int): Int =
    n + m

  property("my magic function") = forAll(Gen.choose(0, 100), Gen.choose(0, 100)) {
    (n: Int, m: Int) =>
      val res = myMagicFunction(n, m)
      ("result > #1" |: (res >= n)) &&
        (res >= m) :| "result > #2" &&
        (res <= n + m) :| "result not sum"
  }

  val smallIntPairsGen = for {
    n <- Gen.choose(0, 10000)
    m <- Gen.choose(0, 10000)
  } yield (n, m)

  val arbitraryPair: Arbitrary[(Int, Int)] = Arbitrary(smallIntPairsGen)

  property("my magic function 2") = forAll(smallIntPairsGen) {
    (pair: (Int, Int)) =>
      val (n, m) = pair
      val res = myMagicFunction(n, m)
      ("result > #1" |: (res >= n)) &&
        (res >= m) :| "result > #2" &&
        (res <= n + m) :| "result not sum"
  }

  sealed abstract class Tree

  case class Node(left: Tree, right: Tree, v: Int) extends Tree

  case object Leaf extends Tree

  import Gen._
  import Arbitrary._

  val genLeaf = const(Leaf)
  val genNode = for {
    v <- arbitrary[Int]
    left <- genTree
    right <- genTree
  } yield Node(left, right, v)

  def genTree: Gen[Tree] = oneOf(genLeaf, genNode)

}
