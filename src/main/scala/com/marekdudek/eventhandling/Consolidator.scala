package com.marekdudek.eventhandling

class Consolidator(observed: List[BankAccount2]) extends Subsciber {

  observed.foreach(_ subscribe this)
  compute()

  private var total: Int = _

  def totalBalalnce = total

  private def compute() =
    total = observed.map(_.currentBalance).sum

  def handler(publisher: Publisher): Unit =
    compute()
}
