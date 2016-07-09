package com.marekdudek.evensimulation

abstract class Circuits extends Gates {

  def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire): Unit = {
    val d, e = new Wire
    orGate(a, b, d)
    andGate(a, b, c)
    inverter(c, e)
    andGate(d, e, s)
  }

  def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire): Unit = {
    val s, c1, c2 = new Wire
    halfAdder(b, cin, s, c1)
    halfAdder(a, s, sum, c2)
    orGate(c1, c2, cout)
  }

  def f(a: Wire, b: Wire, c: Wire): Unit = {
    val d, e, f, g = new Wire
    inverter(a, d)
    inverter(b, e)
    andGate(a, e, f)
    andGate(b, d, g)
    orGate(f, g, c)
  }
}
