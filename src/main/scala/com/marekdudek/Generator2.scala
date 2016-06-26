package com.marekdudek

import scala.collection.immutable.Nil

trait Generator2[+T] {

  self =>

  def generate: T

  def map[S](f: T => S): Generator2[S] =
    new Generator2[S] {
      def generate: S =
        f(self.generate)
    }

  def flatMap[S](f: T => Generator2[S]): Generator2[S] =
    new Generator2[S] {
      def generate: S =
        f(self.generate).generate
    }
}

object Generator2 {

  val integers = new Generator2[Int] {

    private val rand = new java.util.Random

    def generate =
      rand.nextInt()
  }

  val booleans =
    for (x <- integers)
      yield x > 0

  def pairs[T, U](t: Generator2[T], u: Generator2[U]): Generator2[(T, U)] =
    t.flatMap {
      x => u map { y => (x, y) }
    }


  def single[T](x: T): Generator2[T] =
    new Generator2[T] {
      def generate = x
    }

  def choose(lo: Int, hi: Int): Generator2[Int] =
    for (x <- integers)
      yield lo + (x % (hi - lo))

  def oneOf[T](xs: T*): Generator2[T] =
    for (idx <- choose(0, xs.length))
      yield xs(idx)

  def emptyLists: Generator2[Nil.type] =
    single(Nil)

  def nonEmptyLists: Generator2[List[Int]] =
    for {
      head <- integers
      tail <- lists
    } yield head :: tail

  def lists: Generator2[List[Int]] =
    for {
      isEmpty <- booleans
      list <- if (isEmpty) emptyLists else nonEmptyLists
    } yield list

  def leafs: Generator2[Leaf] =
    for {
      number <- integers
    } yield Leaf(number)

  def inners: Generator2[Inner] =
    for {
      l <- trees
      r <- trees
    } yield Inner(l, r)

  def trees: Generator2[Tree] =
    for {
      isLeaf <- booleans
      tree <- if (isLeaf) leafs else inners
    } yield tree
}
