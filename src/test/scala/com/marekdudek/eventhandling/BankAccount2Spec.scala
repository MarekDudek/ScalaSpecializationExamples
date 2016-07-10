package com.marekdudek.eventhandling

import org.scalatest.{FlatSpec, Matchers}

import scala.language.reflectiveCalls

class BankAccount2Spec extends FlatSpec with Matchers {

  "bank account" should "be observable" in {
    // given
    val account = new BankAccount2

    val subsciber = new Subsciber {

      private var balanceHistory = List[Int]()

      def getBalanceHistory = balanceHistory.reverse

      def handler(publisher: Publisher): Unit = {
        val account = publisher.asInstanceOf[BankAccount2]
        balanceHistory = account.currentBalance :: balanceHistory
      }
    }

    account.subscribe(subsciber)
    // when
    account.deposit(1000)
    account.withdraw(300)
    // then
    subsciber.getBalanceHistory shouldBe List(1000, 700)
  }

  "consolidator" should "work" in {
    // given
    val acc1 = new BankAccount2
    acc1.deposit(1000)
    val acc2 = new BankAccount2
    acc2.deposit(500)
    // when
    val consolidator = new Consolidator(List(acc1, acc2))
    consolidator.totalBalalnce shouldBe 1500
  }
}
