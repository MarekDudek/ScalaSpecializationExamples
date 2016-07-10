package com.marekdudek.eventhandling

trait Subsciber {

  def handler(publisher: Publisher): Unit
}