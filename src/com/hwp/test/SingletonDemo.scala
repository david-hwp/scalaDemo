package com.hwp.test

/**
  * Created by David on 2016/12/23 0023.
  */
object SingletonRun {

  val singletonDemo = new SingletonDemo(1)
  //  def main(args: Array[String]): Unit = {
  //
  //  }

}

class SingletonDemo(val numInit: Int) {
  var money: Int = numInit

  def addMoney(num: Int): Unit = {
    money += num
  }

  def reduceMoney(num: Int): Unit = {
    money -= num
  }
}

