package com.marekdudek

import org.scalatest.{FlatSpec, Matchers}

class BookSpec extends FlatSpec with Matchers {

  private val books: Set[Book] = Set(
    Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java", authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers", authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(title = "Java Puzzlers2", authors = List("Bloch, Joshua", "Other, Guy")),
    Book(title = "Programming in Scala", authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
  )

  "books" should "be searchable by author" in {
    // when
    val booksByBird = for (b <- books; a <- b.authors if a startsWith "Bird,") yield b.title
    // then
    booksByBird shouldBe Set("Introduction to Functional Programming")
  }

  "books" should "be searchable by title" in {
    // when
    val booksWithProgram = for (b <- books if b.title contains "Program") yield b.title
    // then
    booksWithProgram shouldBe Set(
      "Structure and Interpretation of Computer Programs",
      "Introduction to Functional Programming",
      "Programming in Scala"
    )
  }

  "books" should "be searchable for common author" in {
    // when
    val authorsOfMultipleBooks = for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1
    // then
    authorsOfMultipleBooks shouldBe Set("Bloch, Joshua")
  }

  "for expression" should "be expressed in terms of higher-order functions" in {

    // when
    val booksByBird2 = books.flatMap {
      b => for (
        a <- b.authors if a.contains("Bird,")
      ) yield b.title
    }
    // then
    booksByBird2 shouldBe Set("Introduction to Functional Programming")

    // when
    val booksByBird3 = books.flatMap {
      b => for {
        a <- b.authors.withFilter(a => a.contains("Bird,"))
      } yield b.title
    }
    // then
    booksByBird3 shouldBe Set("Introduction to Functional Programming")

    // when
    val booksByBird4 = books.flatMap(
      b => b.authors
        .withFilter(a => a.contains("Bird,"))
        .map(a => b.title)
    )
    // then
    booksByBird4 shouldBe Set("Introduction to Functional Programming")
  }
}
