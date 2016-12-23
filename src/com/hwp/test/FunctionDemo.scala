package com.hwp.test

import java.util.Date

/**
  * 函数的应用
  */
object FunctionRun {
  def main(args: Array[String]): Unit = {
    val demo = new FunctionDemo()
    //    println(demo.add(1, 2) + 1)
    //    demo.printSum(2, 3)
    //    demo.functionWithDefaultArgument()
    //    demo.canChangeLengthFunction(2, 3, 4, 5, 6)
    //    demo.lazyDemo()
    //    for (i <- 1 to 10)
    //      println(i + " 的阶乘为: " + demo.factorial(i))
    //    demo.noNameFunction()
    //    demo.dataBound()
    println(demo.curringDemo(2)(3)(4))
  }
}

/**
  * 函数的传名调用
  * 将未计算的参数表达式直接应用到函数内部
  */
object CallByName {
  def main(args: Array[String]) {
    delayed(time())
  }

  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }

  def delayed(t: => Long) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }
}

/**
  * 函数的传值调用
  * 先计算参数表达式的值，再应用到函数内部
  */
object CallByValue {
  def main(args: Array[String]) {
    delayed(time())
  }

  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }

  def delayed(t: Long) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }
}

class FunctionDemo {
  /**
    * 变量声明
    */
  def variableDemo(): Unit = {
    val hwp_stay = "haoren"
    var hwp_temple = "haoren"
    //  hwp_stay = hwp_stay+"_hahaha" //val静态变量，不可重新赋值
    hwp_temple = hwp_temple + "_hahaha"
    println(hwp_stay)
    println(hwp_temple)
  }

  /**
    * 有返回值函数的定义
    *
    * @param x
    * @param y
    * @return
    */
  def add(x: Int, y: Int): Int = {
    x - y
    x * y
    x / y
    return x + y //scala以最后一行的结果作为函数返回值，不需要加return关键字(如果一定要加，必须在"="号之前指定方法返回值类型)
  }

  /**
    * 无返回值函数的定义
    *
    * @param x
    * @param y
    */
  def printSum(x: Int, y: Int): Unit = {
    println("the sum is : " + (x + y))
  }

  /**
    * 调用改方法时如果没有传参数，则会使用默认的hwp
    *
    * @param name
    */
  def functionWithDefaultArgument(name: String = "hwp"): Unit = {
    println(name)
  }

  /**
    * 参数不固定的方法
    *
    * @param x
    */
  def canChangeLengthFunction(x: Int*): Unit = {
    println(x.size)
    for (i <- x) {
      println(i)
    }
  }

  /**
    * lazy变量
    * 正常情况下使用val或者var声明变量时是直接分配内存空间使用的
    * 当使用lazy关键字时，这个常/变量只有在使用的时候才会被分配内存
    */
  def lazyDemo(): Unit = {
    lazy val a = 1
    println(a)
  }

  /**
    * 递归计算阶乘
    *
    * @param n
    * @return
    */
  def factorial(n: BigInt): BigInt = {
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }

  /**
    * 匿名函数
    */
  def noNameFunction(): Unit = {
    val inc = (x: Int) => x + 1
    println(inc(5))
    val mul = (x: Int, y: Int, z: Int) => (x + y) * z
    println(mul(1, 2, 3))
    val userDir = () => System.getProperty("user.dir") //无参匿名函数
    println(userDir)
  }

  /**
    * 偏应用函数
    * 不需要提供函数需要的所有参数，只需要提供部分，或不提供所需参数
    */
  def dataBound(): Unit = {
    val date = new Date
    val log = (date: Date, message: String) => println("date: " + date + "___________________message: " + message)
    val logWithDataBound = log(date, _: String)
    logWithDataBound("wo")
    logWithDataBound("shi")
    logWithDataBound("hwp")
  }

  /**
    * 函数柯里化
    *
    * @param x
    * @param y
    * @param z
    * @return
    */
  def curringDemo(x: Int)(y: Int)(z: Int): Int = {
    x + y * z
  }

}
