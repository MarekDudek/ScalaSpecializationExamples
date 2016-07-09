package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class BankAccountSpec extends FlatSpec with Matchers {

  "creating bank account" should "work" in {
    // given
    val account = new BankAccount
    // when
    account deposit 50
    account withdraw 20
    account withdraw 20
    // then
    val error = the[Error] thrownBy (account withdraw 15)
    error.getMessage shouldBe "insufficient funds"
  }
}
