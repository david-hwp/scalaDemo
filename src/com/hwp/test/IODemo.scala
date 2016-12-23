package com.hwp.test

import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Scala 文件I/O使用方法
  */
object IODemoRun {
  def main(args: Array[String]): Unit = {
    //    print("请输入: ")
    //    val line = Console.readLine
    //    println("谢谢，你输入的是: " + line)

    val iODemo = new IODemo
    //    iODemo.writeFile()
    iODemo.readFile()
  }
}

/**
  * Scala 进行文件写操作，直接用的都是 java中 的 I/O 类 （java.io.File)
  */
class IODemo {
  def writeFile(): Unit = {
    val writer = new PrintWriter(new File("D:\\scalaIOTest.txt"))
    for (i <- 1 to 10) {
      //      writer.write("hwp" + i)
      //      writer.write("\n")
      writer.println("hwp" + i)
    }
    writer.close()
  }

  def readFile(): Unit = {
    val lines = Source.fromFile("D:\\scalaIOTest.txt").getLines()
    while (lines.hasNext) {
      println(lines.next())
    }
  }

}
