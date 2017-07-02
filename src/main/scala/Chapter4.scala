import java.io.BufferedReader
import java.io.InputStreamReader

import java.util.Calendar

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.JavaConversions.propertiesAsScalaMap

import scala.collection.immutable.TreeMap

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.LinkedHashMap

object Main4 extends App {
  val ch3 = new Chapter4
  val map = ch3.createAnItemsToPricesMap
  println("A sample map of items to prices " + map)
  println(
    "map of items modified to 90% of original prices " + ch3
      .modifyMapDiscountingPricesBy10Percent(map))
  println(
    "Word count map of a sample log file is " + ch3
      .readTextFileAndBuildWordCountMap("jmh-output.log"))
  println(
    "Immutable Word count map of a sample log file is " + ch3
      .readTextFileAndBuildWordCountMap("jmh-output.log"))
  println(
    "Sorted Word count map of a sample log file is " + ch3
      .readTextFileAndBuildWordCountTreeMap("jmh-output.log"))
  println(
    "Sorted Word count map using j.u.TreeMap of a sample log file is " + ch3
      .readTextFileAndBuildWordCountUsingJavaTreeMap("jmh-output.log"))
  println(
    "Linked Hash Map of Weekday strings to equivalent Java Calendar Weekday constants is " + ch3.buildWeekdayToJavaCalendarDayConstantMap)
  ch3.printJavaSystemProperties
  val x = for (i <- 1 to 10) yield i
  println("Min and Max of an array from 1 to 10 is " + ch3.minmax(x.toArray))
  println(
    "lteqgt of an array from 1 to 10 compared to 5 is " + ch3.lteqgt(x.toArray,
                                                                     5))
  println(
    "Zipping two strings together results in a sequence of pairs created, each containing the characters from the equivalent positions of strings. I'm not sure what plausible use case fits in here, but I read an answer that says it could be used to find differences between given two strings by applying a filter on those two characters. What I understand is, zip will only consider the min length of two strings, so if a string s1 is zipped with string s2, the sequence of pairs will have a length that is min(s1, s2). Also, if you want to transform one word to another, let's say for message transmission purposes, where plain text shouldn't be sent as is. This way you could scramble a message once we have a mapping of each character source to corresponding character target.")
}

class Chapter4 {
  def createAnItemsToPricesMap(): Map[String, Double] = {
    Map("item1" -> 50.0, "item2" -> 49.1, "item3" -> 101.2)
  }

  def modifyMapDiscountingPricesBy10Percent(
      map: Map[String, Double]): Map[String, Double] = {
    for ((k, v) <- map)
      yield (k, (v * 9) / 10)
  }

  def readTextFileAndBuildWordCountMap(file: String): Map[String, Int] = {
    val reader = new BufferedReader(
      new InputStreamReader(getClass().getResourceAsStream(file)));
    var line: String = reader.readLine()
    val map = new HashMap[String, Int]
    while (line ne null) {
      //println("Line read from file - " + line)
      val splits = line.split(" ");
      for (word <- splits) {
        map.put(word, map.getOrElse(word, 0) + 1)
      }
      line = reader.readLine()
    }
    reader.close()
    map
  }

  def readTextFileAndBuildWordCountImmutableMap(
      file: String): scala.collection.immutable.Map[String, Int] = {
    val reader = new BufferedReader(
      new InputStreamReader(getClass().getResourceAsStream(file)));
    var line: String = reader.readLine()
    var map = new scala.collection.immutable.HashMap[String, Int]
    while (line ne null) {
      //println("Line read from file - " + line)
      val splits = line.split(" ");
      for (word <- splits) {
        map = map + (word -> 1)
      }
      line = reader.readLine()
    }
    reader.close()
    map
  }

  def readTextFileAndBuildWordCountTreeMap(
      file: String): scala.collection.immutable.Map[String, Int] = {
    val reader = new BufferedReader(
      new InputStreamReader(getClass().getResourceAsStream(file)));
    var line: String = reader.readLine()
    var map = new TreeMap[String, Int]
    while (line ne null) {
      //println("Line read from file - " + line)
      val splits = line.split(" ");
      for (word <- splits) {
        map = map + (word -> 1)
      }
      line = reader.readLine()
    }
    reader.close()
    map
  }

  def readTextFileAndBuildWordCountUsingJavaTreeMap(
      file: String): scala.collection.mutable.Map[String, Int] = {
    val reader = new BufferedReader(
      new InputStreamReader(getClass().getResourceAsStream(file)));
    var line: String = reader.readLine()
    var map: Map[String, Int] = new java.util.TreeMap[String, Int]
    while (line ne null) {
      //println("Line read from file - " + line)
      val splits = line.split(" ");
      for (word <- splits) {
        map.put(word, map.getOrElse(word, 0) + 1)
      }
      line = reader.readLine()
    }
    reader.close()
    map
  }

  def buildWeekdayToJavaCalendarDayConstantMap()
    : LinkedHashMap[String, Int] = {
    LinkedHashMap(
      "Monday" -> Calendar.MONDAY,
      "Tuesday" -> Calendar.TUESDAY,
      "Wednesday" -> Calendar.WEDNESDAY,
      "Thursday" -> Calendar.THURSDAY,
      "Friday" -> Calendar.FRIDAY,
      "Saturday" -> Calendar.SATURDAY,
      "Sunday" -> Calendar.SUNDAY
    )
  }

  def printJavaSystemProperties() {
    val map: Map[String, String] = System.getProperties()
    var maxKeySize = 0
    for ((k, v) <- map)
      if (k.length > maxKeySize)
        maxKeySize = k.length
    println("Max key length is " + maxKeySize)
    for ((k, v) <- map)
      println(
        String
          .format("%-" + maxKeySize + "s", k) + "\t" + "|" + "\t" + k.mkString)
  }

  def minmax(values: Array[Int]): (Int, Int) = {
    (values.min, values.max)
  }

  def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
    (values.filter(_ < v).length,
     values.filter(_ == v).length,
     values.filter(_ > v).length)
  }
}
