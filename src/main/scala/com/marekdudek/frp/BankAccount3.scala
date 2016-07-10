package com.marekdudek.frp

class BankAccount3 {

  val balance2 = com.marekdudek.frp.Var(0)

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      val b = balance2()
      balance2() = b + amount
    }

  def withdraw(amount: Int): Unit = {
    if (amount > 0 && amount <= balance2()) {
      val b = balance2()
      balance2() = b - amount
    } else {
      throw new Error("insufficient funds")
    }
  }
}
