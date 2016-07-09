package com.marekdudek.evensimulation

abstract class Simulation {

  type Action = () => Unit

  case class Event(time: Int, action: Action)

  private type Agenda = List[Event]
  private var agenda: Agenda = List()

  private var curtime = 0

  def currentTime: Int = curtime

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val event = Event(curtime + delay, () => block)
    agenda = insert(agenda, event)
  }

  private def insert(ag: Agenda, event: Event): Agenda =
    ag match {
      case e :: es if e.time <= event.time =>
        e :: insert(es, event)
      case _ =>
        event :: ag
    }

  def run(): Unit = {
    loop()
  }

  private def loop(): Unit =
    agenda match {
      case e :: es =>
        agenda = es
        curtime = e.time
        e.action()
        loop()
      case Nil =>
    }
}
