package com.marekdudek.frp

object Consolidator3 {

  def consolidated(accounts: List[BankAccount3]): Signal[Int]  = {
    Signal(accounts.map(_.balance2()).sum)
  }
}
