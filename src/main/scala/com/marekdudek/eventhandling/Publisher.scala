package com.marekdudek.eventhandling

trait Publisher {

  private var subscribers: Set[Subsciber] = Set()

  def subscribe(subscriber: Subsciber): Unit =
    subscribers += subscriber

  def unsubscribe(subsciber: Subsciber): Unit =
    subscribers -= subsciber

  def publish(): Unit =
    subscribers.foreach(_ handler this)
}

