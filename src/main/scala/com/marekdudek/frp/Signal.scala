package com.marekdudek.frp

import scala.util.DynamicVariable

class Signal[T](expr: => T) {

  import Signal._

  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)

  def apply(): T = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }

  def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }

  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_ computeValue())
    }
  }

  def value =
    myValue
}

object NoSignal extends Signal[Nothing](???) {

  override def computeValue() =
    ()
}

object Signal {

  private val caller = new DynamicVariable[Signal[_]](NoSignal)

  def apply[T](expr: => T): Signal[T] =
    new Signal(expr)
}

class Var[T](expr: => T) extends Signal[T](expr) {

  override def update(expr: => T): Unit =
    super.update(expr)
}

object Var {

  def apply[T](expr: => T): Var[T] =
    new Var(expr)
}

class StackableVariable[T](init: T) {

  private var values: List[T] = List(init)

  def value: T = values.head

  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try
      op
    finally
      values = values.tail
  }
}