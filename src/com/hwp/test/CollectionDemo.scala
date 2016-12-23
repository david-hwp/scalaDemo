package com.hwp.test

import scala.collection.SortedMap
/**
  * 集合的应用
  */
object CollectionRun {
  def main(args: Array[String]): Unit = {
    //    val demo = new CollectionDemo()
    //    demo.forLoopDemo()
    //    demo.forLoopDemo1()
    //    demo.forLoopWithFunction()
    //    val listDemo = new ListDemo()
    //    listDemo.baseOperationForList()
    //    listDemo.listReverse()
    //    val setDemo = new SetDemo()
    //    setDemo.createSet()
    //    setDemo.baseOperationForSet()
    //    setDemo.concatSet()
    //    setDemo.intersectionForSet()
    //    val mapDemo = new MapDemo()
    //    mapDemo.baseOperationForMap()
    //    mapDemo.getKeysAndValues()
    //    mapDemo.operationForMap()
    //    mapDemo.LoopForMap()
    //    mapDemo.reverseForMap()
    //    mapDemo.sortByDictForMap()
    //    val tupleDemo = new TupleDemo
    //    tupleDemo.operationForTuple()
    //    val optionDemo = new OptionDemo
    //    optionDemo.baseOperationForOption()
    val iteratorDemo = new IteratorDemo
    iteratorDemo.baseOperationForIterator()
  }
}

class CollectionDemo {
  /**
    * for循环
    */
  def forLoopDemo(): Unit = {
    for (i <- 1 to(10, 2)) {
      println(i)
    }
    val arr = Set("1111", "2222", "3333", "4444")
    var temp = arr.toSeq
    for (i <- 0 to temp.length) {
      println(i)
    }
    for (x <- arr) {
      println(x)
    }
  }

  /**
    * foreach循环每个元素执行自定义函数
    */
  def forLoopDemo1(): Unit = {
    val xs = List(1, 2, 3, 4, 5, 6)

    def myPrint(x: Int) {
      println(x)
    }

    xs foreach myPrint
  }

  /**
    * Scala的循环表达式中没有continue和break语句，但是可以通过一个Boolean变量和嵌套函数中return来实现
    */
  def forLoopWithFunction(): Unit = {
    val xs = List(1, 2, 3, 4, 5, 6)
    for (i <- xs if i % 2 == 0) {
      println(i)
    }
  }
}

/**
  * list集合的应用
  * Scala 列表类似于数组，它们所有元素的类型都相同，
  * 但是它们也有所不同：
  * 列表是不可变的，值一旦被定义了就不能改变，其次列表 具有递归的结构（也就是链接表结构）而数组不是。。
  */
class ListDemo {
  // 字符串列表
  val site: List[String] = List("Tencent", "Google", "Baidu")

  // 整型列表
  val nums: List[Int] = List(1, 2, 3, 4)

  // 空列表
  val empty: List[Nothing] = List()

  // 二维列表
  val dim: List[List[Int]] =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )

  /**
    * 构造列表的两个基本单位是 Nil 和 ::
    * Nil 也可以表示为一个空列表
    * 以上实例我们可以写成如下所示
    */
  def createList(): Unit = {

    // 字符串列表
    val site = "Tencent" :: ("Google" :: ("Baidu" :: Nil))

    // 整型列表
    val nums = 1 :: (2 :: (3 :: (4 :: Nil)))

    // 空列表
    val empty = Nil

    // 二维列表
    val dim = (1 :: (0 :: (0 :: Nil))) ::
      (0 :: (1 :: (0 :: Nil))) ::
      (0 :: (0 :: (1 :: Nil))) :: Nil
  }

  /**
    * Scala列表有三个基本操作：
    * head 返回列表第一个元素
    * tail 返回一个列表，包含除了第一元素之外的其他元素
    * isEmpty 在列表为空时返回true
    */
  def baseOperationForList(): Unit = {
    val nums = Nil
    println("第一网站是 : " + site.head)
    println("除了第一网站是 : " + site.tail)
    println("查看列表 site 是否为空 : " + site.isEmpty)
    println("查看 nums 是否为空 : " + nums.isEmpty)
  }

  /**
    * list的连接
    */
  def listConcat(): Unit = {
    val site2 = "Facebook" :: ("Taobao" :: Nil)
    //使用:::
    var result = site ::: site2
    println("site1 ::: site2 : " + result)
    //使用Set.:::()方法
    result = site.:::(site2)
    println("site.:::(site2) : " + result)
    //使用concat方法
    result = List.concat(site, site2)
    println("List.concat(site, site2) :" + result)
  }

  /**
    * 通过fill创建指定重复数量的元素列表
    */
  def listFill(): Unit = {
    val site = List.fill(2, 4)("h")
    println(site)
  }

  /**
    * List.tabulate() 方法是通过给定的函数来创建列表。
    * 方法的第一个参数为元素的数量，可以是二维的，
    * 第二个参数为指定的函数，我们通过指定的函数计算结果并返回值插入到列表中，起始值为 0
    */
  def listTabulate(): Unit = {
    // 通过给定的函数创建 5 个元素
    val squares = List.tabulate(6)(n => n * n)
    println("一维 : " + squares)

    // 创建二维列表
    val mul = List.tabulate(4, 5)(_ * _)
    println("多维 : " + mul)
  }

  /**
    * List.reverse 用于将列表的顺序反转
    */
  def listReverse(): Unit = {
    println(site)
    println(site.reverse)
    var si = site.::("hwp")
    println("'::' " + si)
    val l = List("1", "2", "3")
    si = site.:::(l)
    println("':::' " + si)
  }

  /**
    * list 的更多用法
    */
  def moreFunctions(): Unit = {
    /**
      * def +(elem: A): List[A]	为列表预添加元素
      * def ::(x: A): List[A]	在列表开头添加元素
      * def :::(prefix: List[A]): List[A]	在列表开头添加指定列表的元素
      * def ::(x: A): List[A]	在列表开头添加元素 x
      * def addString(b: StringBuilder): StringBuilder	将列表的所有元素添加到 StringBuilder
      * def addString(b: StringBuilder, sep: String): StringBuilder	将列表的所有元素添加到 StringBuilder，并指定分隔符
      * def apply(n: Int): A	通过列表索引获取元素
      * def contains(elem: Any): Boolean	检测列表中是否包含指定的元素
      * def copyToArray(xs: Array[A], start: Int, len: Int): Unit	将列表的元素复制到数组中。
      * def distinct: List[A]	去除列表的重复元素，并返回新列表
      * def drop(n: Int): List[A]	丢弃前n个元素，并返回新列表
      * def dropRight(n: Int): List[A]	丢弃最后n个元素，并返回新列表
      * def dropWhile(p: (A) => Boolean): List[A]	从左向右丢弃元素，直到条件p不成立
      * def endsWith[B](that: Seq[B]): Boolean	检测列表是否以指定序列结尾
      * def equals(that: Any): Boolean	判断是否相等
      * def exists(p: (A) => Boolean): Boolean	判断列表中指定条件的元素是否存在。判断l是否存在某个元素:scala&gt; l.exists(s =&gt; s == "Hah") res7: Boolean = true
      * def filter(p: (A) => Boolean): List[A]	输出符号指定条件的所有元素。过滤出长度为3的元素:scala&gt; l.filter(s =&gt; s.length == 3) res8: List[String] = List(Hah, WOW)
      * def forall(p: (A) => Boolean): Boolean	检测所有元素。例如：判断所有元素是否以"H"开头：scala&gt; l.forall(s =&gt; s.startsWith("H")) res10: Boolean = false
      * def foreach(f: (A) => Unit): Unit	将函数应用到列表的所有元素
      * def head: A	获取列表的第一个元素
      * def indexOf(elem: A, from: Int): Int	从指定位置 from 开始查找元素第一次出现的位置
      * def init: List[A]	返回所有元素，除了最后一个
      * def intersect(that: Seq[A]): List[A]	计算多个集合的交集
      * def isEmpty: Boolean	检测列表是否为空
      * def iterator: Iterator[A]	创建一个新的迭代器来迭代元素
      * def last: A	返回最后一个元素
      * def lastIndexOf(elem: A, end: Int): Int	在指定的位置 end 开始查找元素最后出现的位置
      * def length: Int	返回列表长度
      * def map[B](f: (A) => B): List[B]	通过给定的方法将所有元素重新计算
      * def max: A	查找最大元素
      * def min: A	查找最小元素
      * def mkString: String	列表所有元素作为字符串显示
      * def mkString(sep: String): String	使用分隔符将列表所有元素作为字符串显示
      * def reverse: List[A]	列表反转
      * def sorted[B >: A]: List[A]	列表排序
      * def startsWith[B](that: Seq[B], offset: Int): Boolean	检测列表在指定位置是否包含指定序列
      * def sum: A	计算集合元素之和
      * def tail: List[A]	返回所有元素，除了第一个
      * def take(n: Int): List[A]	提取列表的前n个元素
      * def takeRight(n: Int): List[A]	提取列表的后n个元素
      * def toArray: Array[A]	列表转换为数组
      * def toBuffer[B >: A]: Buffer[B]	返回缓冲区，包含了列表的所有元素
      * def toMap[T, U]: Map[T, U]	List 转换为 Map
      * def toSeq: Seq[A]	List 转换为 Seq
      * def toSet[B >: A]: Set[B]	List 转换为 Set
      * def toString(): String	列表转换为字符串
      */
  }
}

/**
  * set的应用
  * Scala Set(集合)是没有重复的对象集合，所有的元素都是唯一的
  * Scala 集合分为可变的和不可变的集合
  * 默认情况下，
  * Scala 使用的是不可变集合，
  * 如果你想使用可变集合，需要引用 scala.collection.mutable.Set 包
  */
class SetDemo {
  val site = Set("Tencent", "Google", "Baidu")

  /**
    * 创建set数组
    */
  def createSet(): Unit = {
    /**
      * 不可变set
      */
    val set = Set(1, 2, 3)
    println(set.getClass.getName)
    println(set.exists(_ % 2 == 0))
    println(set.drop(1))

    /**
      * 可变set
      */
    val mutableSet = scala.collection.mutable.Set(1, 2, 3)
    println(mutableSet.getClass.getName) // scala.collection.mutable.HashSet

    mutableSet.add(4)
    mutableSet.remove(1)
    mutableSet += 5
    mutableSet -= 2

    println(mutableSet) // Set(5, 3, 4)

    val another = mutableSet.toSet
    println(another.getClass.getName) // scala.collection.immutable.Set
  }

  /**
    * 对于Scala集合的任何操作都可以使用这三个基本操作来表达
    */
  def baseOperationForSet(): Unit = {
    val nums: Set[Int] = Set()

    println("第一网站是 : " + site.head)
    println("除了第一网站是 : " + site.tail)
    println("查看列表 site 是否为空 : " + site.isEmpty)
    println("查看 nums 是否为空 : " + nums.isEmpty)
  }

  /**
    * 集合的连接
    * 你可以使用 ++ 运算符或 Set.++() 方法来连接两个集合。如果元素有重复的就会移除重复的元素
    */
  def concatSet(): Unit = {
    val site1 = Set("hwp", "is", "a", "good", "man")
    var result = site.++(site1)
    println("site1.++(site2): " + result)
    result = site ++ site1
    println("site1 ++ site2 : " + result)
  }

  /**
    * 集合的最大最小值
    */
  def minAndMaxForSet(): Unit = {
    val num = Set(5, 6, 9, 20, 30, 45)

    // 查找集合中最大与最小元素
    println("Set(5,6,9,20,30,45) 集合中的最小元素是 : " + num.min)
    println("Set(5,6,9,20,30,45) 集合中的最大元素是 : " + num.max)
  }

  /**
    * 求集合的交集
    */
  def intersectionForSet(): Unit = {
    val num1 = Set(5, 6, 9, 20, 30, 45)
    val num2 = Set(50, 60, 9, 20, 35, 55)

    println("num1.&(num2) : " + num1.&(num2))
    println("num1.intersect(num2) : " + num1.intersect(num2))
    //    println("num1.&~(num2) : " + num1.&~(num2))
  }

  /**
    * 集合的更多操作
    */
  def moreFunctions(): Unit = {
    /**
      * def +(elem: A): Set[A]	为集合添加新元素，x并创建一个新的集合，除非元素已存在
      * def -(elem: A): Set[A]	移除集合中的元素，并创建一个新的集合
      * def contains(elem: A): Boolean	如果元素在集合中存在，返回 true，否则返回 false。
      * def &(that: Set[A]): Set[A]	返回两个集合的交集
      * def &~(that: Set[A]): Set[A]	返回两个集合的差集
      * def +(elem1: A, elem2: A, elems: A*): Set[A]	通过添加传入指定集合的元素创建一个新的不可变集合
      * def ++(elems: A): Set[A]	合并两个集合
      * def -(elem1: A, elem2: A, elems: A*): Set[A]	通过移除传入指定集合的元素创建一个新的不可变集合
      * def addString(b: StringBuilder): StringBuilder	将不可变集合的所有元素添加到字符串缓冲区
      * def addString(b: StringBuilder, sep: String): StringBuilder	将不可变集合的所有元素添加到字符串缓冲区，并使用指定的分隔符
      * def apply(elem: A)	检测集合中是否包含指定元素
      * def count(p: (A) => Boolean): Int	计算满足指定条件的集合元素个数
      * def copyToArray(xs: Array[A], start: Int, len: Int): Unit	复制不可变集合元素到数组
      * def diff(that: Set[A]): Set[A]	比较两个集合的差集
      * def drop(n: Int): Set[A]]	返回丢弃前n个元素新集合
      * def dropRight(n: Int): Set[A]	返回丢弃最后n个元素新集合
      * def dropWhile(p: (A) => Boolean): Set[A]	从左向右丢弃元素，直到条件p不成立
      * def equals(that: Any): Boolean	equals 方法可用于任意序列。用于比较系列是否相等。
      * def exists(p: (A) => Boolean): Boolean	判断不可变集合中指定条件的元素是否存在。
      * def filter(p: (A) => Boolean): Set[A]	输出符合指定条件的所有不可变集合元素。
      * def find(p: (A) => Boolean): Option[A]	查找不可变集合中满足指定条件的第一个元素
      * def forall(p: (A) => Boolean): Boolean	查找不可变集合中满足指定条件的所有元素
      * def foreach(f: (A) => Unit): Unit	将函数应用到不可变集合的所有元素
      * def head: A	获取不可变集合的第一个元素
      * def init: Set[A]	返回所有元素，除了最后一个
      * def intersect(that: Set[A]): Set[A]	计算两个集合的交集
      * def isEmpty: Boolean	判断集合是否为空
      * def iterator: Iterator[A]	创建一个新的迭代器来迭代元素
      * def last: A	返回最后一个元素
      * def map[B](f: (A) => B): immutable.Set[B]	通过给定的方法将所有元素重新计算
      * def max: A	查找最大元素
      * def min: A	查找最小元素
      * def mkString: String	集合所有元素作为字符串显示
      * def mkString(sep: String): String	使用分隔符将集合所有元素作为字符串显示
      * def product: A	返回不可变集合中数字元素的积。
      * def size: Int	返回不可变集合元素的数量
      * def splitAt(n: Int): (Set[A], Set[A])	把不可变集合拆分为两个容器，第一个由前 n 个元素组成，第二个由剩下的元素组成
      * def subsetOf(that: Set[A]): Boolean	如果集合中含有子集返回 true，否则返回false
      * def sum: A	返回不可变集合中所有数字元素之和
      * def tail: Set[A]	返回一个不可变集合中除了第一元素之外的其他元素
      * def take(n: Int): Set[A]	返回前 n 个元素
      * def takeRight(n: Int):Set[A]	返回后 n 个元素
      * def toArray: Array[A]	将集合转换为数字
      * def toBuffer[B >: A]: Buffer[B]	返回缓冲区，包含了不可变集合的所有元素
      * def toList: List[A]	返回 List，包含了不可变集合的所有元素
      * def toMap[T, U]: Map[T, U]	返回 Map，包含了不可变集合的所有元素
      * def toSeq: Seq[A]	返回 Seq，包含了不可变集合的所有元素
      * def toString(): String	返回一个字符串，以对象来表示
      */
  }

}

/**
  * 所有的值都可以通过键来获取。
  * Map 中的键都是唯一的。
  *
  * Map 也叫哈希表（Hash tables）。
  * Map 有两种类型，可变与不可变，区别在于可变对象可以修改它，而不可变对象不可以。
  * 默认情况下 Scala 使用不可变 Map。如果你需要使用可变集合，你需要显式的引入 import scala.collection.mutable.Map 类
  * 在 Scala 中 你可以同时使用可变与不可变 Map，不可变的直接使用 Map，可变的使用 mutable.Map。以下实例演示了不可变 Map 的应用：
  */
class MapDemo {
  // 空哈希表，键为字符串，值为整型
  var map: Map[Char, Int] = Map()
  map += ('I' -> 1)
  map += ('J' -> 5)
  map += ('K' -> 10)
  map += ('L' -> 100)

  // Map 键值对演示
  val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF", "peru" -> "#CD853F")


  /**
    * map的基本操作
    * keys	返回 Map 所有的键(key)
    * values	返回 Map 所有的值(value)
    * isEmpty	在 Map 为空时返回true
    */
  def baseOperationForMap(): Unit = {
    val nums: Map[Int, Int] = Map()

    println("colors 中的键为 : " + colors.keys)
    println("colors 中的值为 : " + colors.values)
    println("检测 colors 是否为空 : " + colors.isEmpty)
    println("检测 nums 是否为空 : " + nums.isEmpty)
  }

  /**
    * 输出map的key和value
    */
  def getKeysAndValues(): Unit = {
    colors.keys.foreach { i =>
      printf("key: %s,value: %s", i, colors(i))
      println()
    }
  }

  /**
    * map的操作
    */
  def operationForMap(): Unit = {
    //    var result = colors.++(Map("key" -> "value"))
    var result = colors ++ Map("key" -> "value")
    println(result)
    result = colors - "red"
    println(result)
    //    val mapFordDelete = ("azure" -> "#F0FFFF", "peru" -> "#CD853F")
    val mapFordDelete = Set("azure", "peru")
    result = colors -- mapFordDelete
    println(result)
  }

  /**
    * map的两种迭代方式
    */
  def LoopForMap(): Unit = {
    for ((k, v) <- colors) {
      println(k + ":" + v)
    }

    colors.keys.foreach(k =>
      println(k + ":" + colors(k))
    )
  }

  /**
    * map的key,value 反转
    */
  def reverseForMap(): Unit = {
    val colors1 = for ((k, v) <- colors) yield (v, k)
    println(colors1)
  }

  /**
    * map按照字典顺序排序
    */
  def sortByDictForMap(): Unit = {
    var sortedMap: SortedMap[String, String] = SortedMap()
    for ((k, v) <- colors) {
      sortedMap += (k -> v)
    }
    for ((k, v) <- sortedMap) {
      println(k + " : " + v)
    }
  }

  /**
    * 更多的方法
    */
  def moreFunctions(): Unit = {
    /**
      * def ++(xs: Map[(A, B)]): Map[A, B]	返回一个新的 Map，新的 Map xs 组成
      * def -(elem1: A, elem2: A, elems: A*): Map[A, B]	返回一个新的 Map, 移除 key 为 elem1, elem2 或其他 elems。
      * def --(xs: GTO[A]): Map[A, B]	返回一个新的 Map, 移除 xs 对象中对应的 key
      * def get(key: A): Option[B]	返回指定 key 的值
      * def iterator: Iterator[(A, B)]	创建新的迭代器，并输出 key/value 对
      * def addString(b: StringBuilder): StringBuilder	将 Map 中的所有元素附加到StringBuilder，可加入分隔符
      * def addString(b: StringBuilder, sep: String): StringBuilder	将 Map 中的所有元素附加到StringBuilder，可加入分隔符
      * def apply(key: A): B	返回指定键的值，如果不存在返回 Map 的默认方法
      * def clear(): Unit	清空 Map
      * def clone(): Map[A, B]	从一个 Map 复制到另一个 Map
      * def contains(key: A): Boolean	如果 Map 中存在指定 key，返回 true，否则返回 false。
      * def copyToArray(xs: Array[(A, B)]): Unit	复制集合到数组
      * def count(p: ((A, B)) => Boolean): Int	计算满足指定条件的集合元素数量
      * def default(key: A): B	定义 Map 的默认值，在 key 不存在时返回。
      * def drop(n: Int): Map[A, B]	返回丢弃前n个元素新集合
      * def dropRight(n: Int): Map[A, B]	返回丢弃最后n个元素新集合
      * def dropWhile(p: ((A, B)) => Boolean): Map[A, B]	从左向右丢弃元素，直到条件p不成立
      * def empty: Map[A, B]	返回相同类型的空 Map
      * def equals(that: Any): Boolean	如果两个 Map 相等(key/value 均相等)，返回true，否则返回false
      * def exists(p: ((A, B)) => Boolean): Boolean	判断集合中指定条件的元素是否存在
      * def filter(p: ((A, B))=> Boolean): Map[A, B]	返回满足指定条件的所有集合
      * def filterKeys(p: (A) => Boolean): Map[A, B]	返回符合指定条件的的不可变 Map
      * def find(p: ((A, B)) => Boolean): Option[(A, B)]	查找集合中满足指定条件的第一个元素
      * def foreach(f: ((A, B)) => Unit): Unit	将函数应用到集合的所有元素
      * def init: Map[A, B]	返回所有元素，除了最后一个
      * def isEmpty: Boolean	检测 Map 是否为空
      * def keys: Iterable[A]	返回所有的key/p>
      * def last: (A, B)	返回最后一个元素
      * def max: (A, B)	查找最大元素
      * def min: (A, B)	查找最小元素
      * def mkString: String	集合所有元素作为字符串显示
      * def product: (A, B)	返回集合中数字元素的积。
      * def remove(key: A): Option[B]	移除指定 key
      * def retain(p: (A, B) => Boolean): Map.this.type	如果符合满足条件的返回 true
      * def size: Int	返回 Map 元素的个数
      * def sum: (A, B)	返回集合中所有数字元素之和
      * def tail: Map[A, B]	返回一个集合中除了第一元素之外的其他元素
      * def take(n: Int): Map[A, B]	返回前 n 个元素
      * def takeRight(n: Int): Map[A, B]	返回后 n 个元素
      * def takeWhile(p: ((A, B)) => Boolean): Map[A, B]	返回满足指定条件的元素
      * def toArray: Array[(A, B)]	集合转数组
      * def toBuffer[B >: A]: Buffer[B]	返回缓冲区，包含了 Map 的所有元素
      * def toList: List[A]	返回 List，包含了 Map 的所有元素
      * def toSeq: Seq[A]	返回 Seq，包含了 Map 的所有元素
      * def toSet: Set[A]	返回 Set，包含了 Map 的所有元素
      * def toString(): String	返回字符串对象
      */
  }
}

/**
  * 与列表一样，元组也是不可变的，但与列表不同的是元组可以包含不同类型的元素
  * 目前 Scala 支持的元组最大长度为 22
  */
class TupleDemo {
  val tuple1 = (1, 3.14, "hwp")
  val tuple2 = new Tuple3(1, 3.14, "Fred")

  /**
    * 元组的操作
    */
  def operationForTuple(): Unit = {
    //元组的访问
    println(tuple1._1)
    println(tuple1._3)
    //迭代
    tuple1.productIterator.foreach { i => println("Value = " + i) }
    //交换
    //    println(tuple1.swap)
    val t = new Tuple2("www.google.com", "www.runoob.com")

    println("交换后的元组: " + t.swap)
  }
}

/**
  * Option类型的使用方法
  */
class OptionDemo {
  val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF", "peru" -> "#CD853F")
  var option1 = Option
  option1.apply(9)
  option1.apply(9)

  def baseOperationForOption(): Unit = {
    val color1: Option[String] = colors.get("red")
    val color2: Option[String] = colors.get("yellow")
    println("color1 :" + colors.get("red"))
    println("color1 :" + color1.getOrElse()) //getOrElse() 方法来获取元组中存在的元素或者使用其默认的值
    println("color2 :" + color2)
    println("color2 :" + color2.isEmpty) //可以使用 isEmpty() 方法来检测元组中的元素是否为 None
    println("color1 :" + show(color1) + "_____color2 :" + show(color2)) //通过模式匹配类输出匹配值
    /**
      * 模式匹配的例子
      */
    colors.keys.foreach(key =>
      //      println(selectToShow(key))
      println(colors(selectToShow(key)))
    )

    //返回元素个数
    println(color1.productArity)
  }

  /**
    * Option类型的模式匹配方法
    *
    * @param x
    * @return
    */
  def show(x: Option[String]) = x match {
    case Some(s) => s //如果Option为Some说明有值，则返回值
    case None => "" //如果Option为None则返回空字符串
  }

  def selectToShow(x: String) = x match {
    case "red" => "peru"
    case x => x
  }

  /**
    * Option类型更多的方法
    */
  def moreFunctions(): Unit = {
    /**
      * def get: A	获取可选值
      * def isEmpty: Boolean	检测可选类型值是否为 None，是的话返回 true，否则返回 false
      * def productArity: Int	返回元素个数， A(x_1, ..., x_k), 返回 k
      * def productElement(n: Int): Any	获取指定的可选项，以 0 为起始。即 A(x1, ..., x_k), 返回 x(n+1) ， 0 < n < k.
      * def exists(p: (A) => Boolean): Boolean	如果可选项中指定条件的元素是否存在且不为 None 返回 true，否则返回 false。
      * def filter(p: (A) => Boolean): Option[A]	如果选项包含有值，而且传递给 filter 的条件函数返回 true， filter 会返回 Some 实例。 否则，返回值为 None 。
      * def filterNot(p: (A) => Boolean): Option[A]	如果选项包含有值，而且传递给 filter 的条件函数返回 false， filter 会返回 Some 实例。 否则，返回值为 None 。
      * def flatMap[B](f: (A) | => Option[B]): Option[B]	如果选项包含有值，则传递给函数 f 处理后返回，否则返回 None
      * def foreach[U](f: (A) | => U): Unit	如果选项包含有值，则将每个值传递给函数 f， 否则不处理。
      * def getOrElse[B >: A](default: => B) |: B	如果选项包含有值，返回选项值，否则返回设定的默认值。
      * def isDefined: Boolean	如果可选值是 Some 的实例返回 true，否则返回 false。
      * def iterator: Iterator[A]	如果选项包含有值，迭代出可选值。如果可选值为空则返回空迭代器。
      * def map[B](f: (A) | => B): Option[B]	如果选项包含有值， 返回由函数 f 处理后的 Some，否则返回 None
      * def orElse[B >: A](alternative: => Option[B]) |: Option[B]	如果一个 Option 是 None ， orElse 方法会返回传名参数的值，否则，就直接返回这个 Option。
      * def orNull	如果选项包含有值返回选项值，否则返回 null。
      */
  }
}

class IteratorDemo {
  val set = Set("Baidu", "Google", "Runoob", "Taobao")
  val list = List(20, 40, 2, 50, 69, 90)

  /**
    * iterator的基本操作
    */
  def baseOperationForIterator(): Unit = {
    //iterator的两个基本操作
    val it = set.iterator
    while (it.hasNext) {
      println(it.next())
    }
    //查找最大、最小元素
    val ita = list.iterator
    val itb = list.iterator
    println("min: " + ita.min + "---- max: " + itb.max)

    //获得迭代器的长度val
    val itc = list.iterator
    val itd = list.iterator
    println("ita.size 的值: " + itc.size)
    println("itb.length 的值: " + itd.length)
  }

  /**
    * 更多操作
    */
  def moreFunctions(): Unit = {
    /**
      * def hasNext: Boolean	如果还有可返回的元素，返回true。
      * def next(): A	返回迭代器的下一个元素，并且更新迭代器的状态
      * def ++(that: => Iterator[A]): Iterator[A]	合并两个迭代器
      * def ++[B >: A](that :=> GenTraversableOnce[B]) |: Iterator[B]	合并两个迭代器
      * def addString(b: StringBuilder): StringBuilder	添加一个字符串到 StringBuilder b
      * def addString(b: StringBuilder, sep: String): StringBuilder	添加一个字符串到 StringBuilder b，并指定分隔符
      * def buffered: BufferedIterator[A]	迭代器都转换成 BufferedIterator
      * def contains(elem: Any): Boolean	检测迭代器中是否包含指定元素
      * def copyToArray(xs: Array[A], start: Int, len: Int): Unit	将迭代器中选定的值传给数组
      * def count(p: (A) => Boolean): Int	返回迭代器元素中满足条件p的元素总数。
      * def drop(n: Int): Iterator[A]	返回丢弃前n个元素新集合
      * def dropWhile(p: (A) => Boolean): Iterator[A]	从左向右丢弃元素，直到条件p不成立
      * def duplicate: (Iterator[A], Iterator[A])	生成两个能分别返回迭代器所有元素的迭代器。
      * def exists(p: (A) => Boolean): Boolean	返回一个布尔值，指明迭代器元素中是否存在满足p的元素。
      * def filter(p: (A) => Boolean): Iterator[A]	返回一个新迭代器 ，指向迭代器元素中所有满足条件p的元素。
      * def filterNot(p: (A) => Boolean): Iterator[A]	返回一个迭代器，指向迭代器元素中不满足条件p的元素。
      * def find(p: (A) => Boolean): Option[A]	返回第一个满足p的元素或None。注意：如果找到满足条件的元素，迭代器会被置于该元素之后；如果没有找到，会被置于终点。
      * def flatMap[B](f: (A) | => GenTraversableOnce[B]): Iterator[B]	针对迭代器的序列中的每个元素应用函数f，并返回指向结果序列的迭代器。
      * def forall(p: (A) => Boolean): Boolean	返回一个布尔值，指明 it 所指元素是否都满足p。
      * def foreach(f: (A) => Unit): Unit	在迭代器返回的每个元素上执行指定的程序 f
      * def hasDefiniteSize: Boolean	如果迭代器的元素个数有限则返回true（缺省等同于isEmpty）
      * def indexOf(elem: B): Int	返回迭代器的元素中index等于x的第一个元素。注意：迭代器会越过这个元素。
      * def indexWhere(p: (A) => Boolean): Int	返回迭代器的元素中下标满足条件p的元素。注意：迭代器会越过这个元素。
      * def isEmpty: Boolean	检查it是否为空, 为空返回 true，否则返回false（与hasNext相反）。
      * def isTraversableAgain: Boolean	Tests whether this Iterator can be repeatedly traversed.
      * def length: Int	返回迭代器元素的数量。
      * def map[B](f: (A) | => B): Iterator[B]	将 it 中的每个元素传入函数 f 后的结果生成新的迭代器。
      * def max: A	返回迭代器迭代器元素中最大的元素。
      * def min: A	返回迭代器迭代器元素中最小的元素。
      * def mkString: String	将迭代器所有元素转换成字符串。
      * def mkString(sep: String): String	将迭代器所有元素转换成字符串，并指定分隔符。
      * def nonEmpty: Boolean	检查容器中是否包含元素（相当于 hasNext）。
      * def padTo(len: Int, elem: A): Iterator[A]	首先返回迭代器所有元素，追加拷贝 elem 直到长度达到 len。
      * def patch(from: Int, patchElems: Iterator[B], replaced: Int): Iterator[B]	返回一个新迭代器，其中自第 from 个元素开始的 replaced 个元素被迭代器所指元素替换。
      * def product: A	返回迭代器所指数值型元素的积。
      * def sameElements(that: Iterator[_]): Boolean	判断迭代器和指定的迭代器参数是否依次返回相同元素
      * def seq: Iterator[A]	返回集合的系列视图
      * def size: Int	返回迭代器的元素数量
      * def slice(from: Int, until: Int): Iterator[A]	返回一个新的迭代器，指向迭代器所指向的序列中从开始于第 from 个元素、结束于第 until 个元素的片段。
      * def sum: A	返回迭代器所指数值型元素的和
      * def take(n: Int): Iterator[A]	返回前 n 个元素的新迭代器。
      * def toArray: Array[A]	将迭代器指向的所有元素归入数组并返回。
      * def toBuffer: Buffer[B]	将迭代器指向的所有元素拷贝至缓冲区 Buffer。
      * def toIterable: Iterable[A]	Returns an Iterable containing all elements of this traversable or iterator. This will not terminate for infinite iterators.
      * def toIterator: Iterator[A]	把迭代器的所有元素归入一个Iterator容器并返回。
      * def toList: List[A]	把迭代器的所有元素归入列表并返回
      * def toMap[T, U]: Map[T, U]	将迭代器的所有键值对归入一个Map并返回。
      * def toSeq: Seq[A]	将代器的所有元素归入一个Seq容器并返回。
      * def toString(): String	将迭代器转换为字符串
      * def zip[B](that: Iterator[B]) |: Iterator[(A, B)	返回一个新迭代器，指向分别由迭代器和指定的迭代器 that 元素一一对应而成的二元组序列
      */
  }
}
