package com.hwp.test

/**
  * 在 Scala 中，是没有 static 这个东西的，但是它也为我们提供了单例模式的实现方法，那就是使用关键字 object。
  * Scala 中使用单例模式时，除了定义的类之外，还要定义一个同名的 object 对象，它和类的区别是，object对象不能带参数。
  * 当单例对象与某个类共享同一个名称时，他被称作是这个类的伴生对象：companion object(本例中为ObjectRun)。
  * 你必须在同一个源文件里定义类和它的伴生对象。类被称为是这个单例对象的伴生类：companion class。
  * 类和它的伴生对象可以互相访问其私有成员。
  */
object ObjectRun {
  def main(args: Array[String]): Unit = {
    //    val point = new Point(10, 10)
    //    point.move(1, 2)
    //
    //    val location = new Location(10, 10, 10)
    //    location.move(1, 2) //调用子类重写父类的move方法
    //    println("========================")
    //    location.move(1, 2, 3) //调用子类重载的move方法

    //    if (location.isInstanceOf[Point]) {
    //      println("true")
    //      val s = location.asInstanceOf[Point] //子类可以转父类，父类不可以转子类
    //      s.move(1, 2)
    //    }

    //    val item1 = new SimpleItem(2.0, "paper")
    //    val item2 = new SimpleItem(38, "How to read a book")
    //    val items = new Bundle
    //    items addItem item1
    //    items addItem item2
    //    println("Bundle price: " + items.price)
    //    println(items.description)
    //    val singletonDemo = SingletonDemo
    //单例模式
    println(SingletonRun.singletonDemo.money)
    SingletonRun.singletonDemo.addMoney(10)
    println(SingletonRun.singletonDemo.money)
    SingletonRun.singletonDemo.reduceMoney(5)
    println(SingletonRun.singletonDemo.money)
  }
}

class ObjectDemo {

}

/**
  * 类和对象
  * 类是对象的抽象，而对象是类的具体实例。
  * 类是抽象的，不占用内存，而对象是具体的，占用存储空间。
  * 类是用于创建对象的蓝图，它是一个定义包括在特定类型的对象中的方法和变量的软件模板
  */
class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println("父类，x 的坐标点: " + x)
    println("父类，y 的坐标点: " + y)
  }
}

class Location(val xc: Int, val yc: Int, val zc: Int) extends Point(xc: Int, yc: Int) {
  var z: Int = zc

  //  override val xc: Int = 2
  /**
    * 子类重写父类的方法
    * 方法前要加关键字override
    *
    * @param dx
    * @param dy
    */
  override def move(dx: Int, dy: Int): Unit = {
    x = x - dx
    y = y - dy
    println("子类，x 的坐标点 : " + x)
    println("子类，y 的坐标点 : " + y)
  }

  def move(dx: Int, dy: Int, dz: Int) {
    super.move(dx, dy) //调用父类的方法要用move
    z = z + dz
    println("子类，x 的坐标点 : " + x)
    println("子类，y 的坐标点 : " + y)
    println("子类，z 的坐标点 : " + z)
  }
}

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
  override val range = 2
}

/**
  * 抽象类继承的运用
  */
abstract class Item() {
  def price: Double

  def description: String
}

class SimpleItem(val price: Double, val description: String) extends Item {
}

import scala.collection.mutable.ArrayBuffer

class Bundle(var items: ArrayBuffer[Item]) extends Item {
  def this() {
    this(new ArrayBuffer[Item])
  }

  override def price: Double = {
    if (items == null || items.isEmpty) return 0D
    var total = 0D
    items.foreach(total += _.price)
    total
  }

  override def description: String = {
    if (items == null || items.isEmpty) "Nothing in bundle"
    else items.map(_.description).mkString("Items in Bundle: [", ", ", "]")
  }

  def addItem(item: Item) {
    items += item
  }
}

