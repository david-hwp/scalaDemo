package com.hwp.test

/**
  * 数组的应用
  */
object ArrayRun {
  def main(args: Array[String]): Unit = {
    val demo = new ArrayDemo
    demo.createArray()
  }
}

class ArrayDemo {
  /**
    * 创建数组
    */
  def createArray(): Unit = {
    val arr: Array[String] = Array("1", "2", "3")
    val arr1 = Array("1", "2", "3")
    println(arr, arr1)
  }
}
