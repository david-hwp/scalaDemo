package com.hwp.test

/**
  * Scala Trait(特征) 相当于 Java 的接口，实际上它比接口还功能强大。
  * 与接口不同的是，它还可以定义属性和方法的实现。
  * 一般情况下Scala的类只能够继承单一父类，但是如果是 Trait(特征) 的话就可以继承多个，从结果来看就是实现了多重继承
  */
object TraitRun {
  def main(args: Array[String]): Unit = {
    val point2 = new Point2(10, 20)
    point2.move(3, 4)
    point2.print()
  }
}

class TraitDemo {
}

trait PointMoveTrait {
  def move(cx: Int, cy: Int): Unit
}

trait PointPrintTrait {
  def print(): Unit
}

class Point2(xc: Int, yc: Int) extends PointMoveTrait with PointPrintTrait {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
  }

  def print(): Unit = {
    println("x 的坐标点: " + x)
    println("y 的坐标点: " + y)
  }
}
