package com.hwp.test

import scala.util.control.Breaks

/**
  * 循环的使用
  */
object LoopRun {
  def main(args: Array[String]): Unit = {
    val demo = new LoopDemo
    //    demo.whileDemo()
    //    demo.forDemo()
    demo.forAdvancedDemo()
  }
}

class LoopDemo {
  /**
    * while循环
    */
  def whileDemo(): Unit = {
    var i = 20
    val loop = new Breaks
    loop.breakable {
      while (i >= 10) {
        println(i)
        i -= 1
        if (i % 2 == 0) {
          loop.break()
        }
      }
    }
    println("after the loop")
  }

  /**
    * for循环嵌套
    */
  def forDemo(): Unit = {
    val numList1 = List(1, 2, 3, 4, 5)
    val numList2 = List(11, 12, 13)
    val outer = new Breaks
    val inner = new Breaks

    outer.breakable {
      for (a <- numList1) {
        println("Value of a: " + a)
        if (a > 2) {
          outer.break()
        }
        inner.breakable {
          for (b <- numList2) {
            println("Value of b: " + b)
            if (b == 12) {
              inner.break
            }
          }
        } // 内嵌循环中断
      }
    } // 外部循环中断
    println("after the loop")
  }

  /**
    * for循环进阶用法
    */
  def forAdvancedDemo(): Unit = {
    for (i <- 1 to 5) {
      println("for loop use 'to'" + i)
    }

    for (i <- 1 until 5) {
      println("for loop use 'until'" + i)
    }

    //使用分号 (;) 来设置多个区间，它将迭代给定区间所有的可能值
    for (a <- 1 to 5; b <- 1 until 5; c <- 1 to 2) {
      printf("a: %s, b: %s, c: %s", a, b, c)
      println()
    }
    //可以使用分号(;)来为表达式添加一个或多个的过滤条件
    val num = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (a <- num; if a != 3; if a < 8) {
      println("for loop with conditions a: " + a)
    }
    //使用 yield你可以将 for 循环的返回值作为一个变量存储
    val temp = for {a <- num; if a != 3; if a < 8} yield a
    for (a <- temp) {
      println("for loop with yield: " + a)
    }
  }
}
