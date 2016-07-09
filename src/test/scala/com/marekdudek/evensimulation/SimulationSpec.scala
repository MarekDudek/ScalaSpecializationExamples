package com.marekdudek.evensimulation

import org.scalatest.{FlatSpec, Matchers}

class SimulationSpec extends FlatSpec with Matchers {

  "half adder simulation" should "work" in {

    object Sim extends Circuits with Parameters
    import Sim._

    val in1, in2, sum, carry = new Wire
    halfAdder(in1, in2, sum, carry)
    probe("sum", sum)
    probe("carry", carry)

    in1 setSignal true
    Sim.run()

    in2 setSignal true
    Sim.run()

    in1 setSignal false
    Sim.run()
  }
}
