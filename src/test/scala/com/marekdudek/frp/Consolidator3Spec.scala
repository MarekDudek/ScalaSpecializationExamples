package com.marekdudek.frp

import org.scalatest.{FlatSpec, Matchers}

class Consolidator3Spec extends FlatSpec with Matchers {

  "consolidator" should "work" in {
    // given
    val a = new BankAccount3
    val b = new BankAccount3
    val c = new BankAccount3
    // when
    val totalBalance: Signal[Int] = Consolidator3.consolidated(List(a, b, c))

    println(totalBalance.value)

    a deposit 500
    b deposit 1000
    c deposit 300

    println(totalBalance.value)

    val xchange = Signal(246.00)
    val inDollar = Signal(totalBalance() * xchange())

    b withdraw 10

    println(totalBalance.value)
    println(inDollar.value)
  }
}
