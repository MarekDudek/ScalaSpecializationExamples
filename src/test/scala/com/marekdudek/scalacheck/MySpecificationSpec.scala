package com.marekdudek.scalacheck

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

class MySpecificationSpec extends FunSuite with Checkers {

  test("specification") {
    check(MySpecification)
  }
}
