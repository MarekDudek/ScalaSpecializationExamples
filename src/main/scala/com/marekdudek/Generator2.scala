package com.marekdudek

trait Generator2[+T] {

  self =>

  def generate: T

  def map[S](f: T => S): Generator2[S] =
    new Generator2[S] {
      def generate: S =
        f(self.generate)
    }

  def flatMap[S](f: T => Generator2[S]): Generator2[S] =
    new Generator2[S] {
      def generate: S =
        f(self.generate).generate
    }
}
