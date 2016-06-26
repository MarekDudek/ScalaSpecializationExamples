package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class JsonSpec extends FlatSpec with Matchers {

  "JSON" should "be constructed" in {

    val data = JObj(Map(
      "firstName" -> JStr("John"),
      "lastName" -> JStr("Smith"),
      "address" -> JObj(Map(
        "streetAddress" -> JStr("21 2nd Street"),
        "state" -> JStr("NY"),
        "postalCode" -> JNum(10021)
      )),
      "phoneNumbers" -> JSeq(List(
        JObj(Map(
          "type" -> JStr("home"), "number" -> JStr("212 555-1234")
        )),
        JObj(Map(
          "type" -> JStr("fax"), "number" -> JStr("646 555-4567")
        ))
      ))
    ))

    val list = for {
      JObj(bindings) <- List(data)
      JSeq(phones) = bindings("phoneNumbers")
      JObj(phone) <- phones
      JStr(digits) = phone("number")
      if digits startsWith "212"
    } yield (bindings("firstName"), bindings("lastName"))

    list shouldBe List((JStr("John"), JStr("Smith")))
  }

  "partial function" should "be testable for being defined" in {

    val f: PartialFunction[String, String] = {
      case "ping" => "pong"
    }

    f("ping") shouldBe "pong"

    f isDefinedAt "ping" shouldBe true
    f isDefinedAt "abc" shouldBe false
  }
}
