package com.hwp.test

/**
  * 提取器是从传递给它的对象中提取出构造该对象的参数。
  * Scala 标准库包含了一些预定义的提取器，我们会大致的了解一下它们。
  * Scala 提取器是一个带有unapply方法的对象。
  * unapply方法算是apply方法的反向操作：unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。
  */
object ExtractorRun {
  def main(args: Array[String]): Unit = {
    /**
      * ExtractorBaseDemo中定义了两个方法： apply 和 unapply 方法。
      * 通过 apply 方法我们无需使用 new 操作就可以创建对象。
      * 所以你可以通过语句ExtractorBaseDemo("hwp", "sina.com") 来构造一个字符串 "hwp@sina.com"。
      * unapply方法算是apply方法的反向操作：unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。
      * 实例中我们使用 Unapply 方法从对象中提取用户名和邮件地址的后缀
      */
    val result = ExtractorBaseDemo("hwp", "sina.com") //等效于 val result = ExtractorBaseDemo.apply("hwp", "sina.com")
    println(result)
    val resultUnapply = ExtractorBaseDemo.unapply("hwp@sina.com")
    println(resultUnapply)

    /**
      * 提取器使用模式匹配
      */
    val x = TestDemo(5)
    println(x)

    x match {
      case TestDemo(num) => println(x + " 是 " + num + " 的两倍！")
      //unapply 被调用
      case _ => println("无法计算")
    }
  }
}

class ExtractorDemo {}

/**
  * 以下实例演示了邮件地址的提取器对象
  */
object ExtractorBaseDemo {

  def apply(name: String, domain: String): String = {
    name + "@" + domain
  }

  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    parts.length match {
      case 2 => Some(parts(0), parts(1))
      case _ => None
    }
  }
}

object TestDemo {
  def apply(x: Int) = x * 2

  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
}
