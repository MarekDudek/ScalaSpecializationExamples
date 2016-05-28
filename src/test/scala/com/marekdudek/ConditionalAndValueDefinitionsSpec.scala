package com.marekdudek

import com.marekdudek.Common.loop
import com.marekdudek.ConditionalAndValueDefinitions.and
import org.scalatest.{FlatSpec, Matchers}

class ConditionalAndValueDefinitionsSpec extends FlatSpec with Matchers {

  "and function" should "behave as && operator" in {

    and(x = true, y = true) shouldBe true

    and(x = false, y = true) shouldBe false
    and(x = true, y = false) shouldBe false
    and(x = false, y = false) shouldBe false
  }

  "and function" should "not necessarily evaluate second parameter" in {

    and(x = false, y = loop) shouldBe false
  }
}
