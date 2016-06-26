package com.marekdudek

trait Monad[T] {

  // a.k.a bind
  def flatMap[U](f: T => Monad[U]): Monad[U]

  def map[U](f: T => U): Monad[U] =
    this flatMap {
      x => unit(f(x))
    }

  def unit[T](x: T): Monad[T]
}
