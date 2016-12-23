package com.hwp.test

import java.io.{FileNotFoundException, FileReader, IOException}

/**
  * scala的异常处理(跟java类似)
  */
object ExceptionRun {
  def main(args: Array[String]): Unit = {
    val exceptionDemo = new ExceptionDemo
    exceptionDemo.base()
  }
}

class ExceptionDemo {
  def base(): Unit = {
    try {
      val f = new FileReader("input.txt")
    } catch {
      case ex: FileNotFoundException =>
        println("Missing file exception" + ex.getMessage)
      case ex: IOException =>
        println(ex.getMessage)
    } finally {
      println("Exiting finally...")
    }
  }
}
