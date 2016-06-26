package com.marekdudek

import scala.util.control.NonFatal

abstract class MTry[+T] {

  def flatMap[U](f: T => MTry[U]): MTry[U] =
    this match {
      case Success(x) =>
        try
          f(x)
        catch {
          case NonFatal(ex) => Failure(ex)
        }
      case fail: Failure => fail
    }

  def map[U](f: T => U): MTry[U] =
    this match {
      case Success(x) => MTry(f(x))
      case fail: Failure => fail
    }
}

case class Success[T](x: T) extends MTry[T]

case class Failure(ex: Throwable) extends MTry[Nothing]


object MTry {

  def apply[T](expr: => T): MTry[T] =
    try
      Success(expr)
    catch {
      case NonFatal(ex) => Failure(ex)
    }
}
