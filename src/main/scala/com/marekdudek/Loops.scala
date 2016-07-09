package com.marekdudek

import scala.annotation.tailrec
import scala.language.reflectiveCalls

object Loops {

  def power(x: Double, exp: Int): Double = {
    var r = 1.0
    var i = exp
    while (i > 0) {
      r *= x
      i -= 1
    }
    r
  }

  @tailrec
  def whilefun(condition: => Boolean)(command: => Unit): Unit =
    if (condition) {
      command
      whilefun(condition)(command)
    } else {
      ()
    }

  def power2(x: Double, exp: Int): Double = {
    var r = 1.0
    var i = exp
    whilefun(i > 0) {
      r *= x
      i -= 1
    }
    r
  }

  def repeat(command: => Unit)(condition: => Boolean): Unit = {
    command
    if (!condition) {
      repeat(command)(condition)
    }
  }

  type HasUntil = Object {def until(condition: => Boolean): Unit}

  def repeat2(command: => Unit): HasUntil = new {

    @tailrec
    final def until(condition: => Boolean): Unit = {
      command
      if (!condition) {
        until(condition)
      }
    }
  }

  class Until(command: => Unit) {

    @tailrec
    final def until(condition: => Boolean): Unit = {
      command
      if (!condition) {
        until(condition)
      }
    }
  }

  def repeat3(command: => Unit): Until =
    new Until(command)
}
