import java.io.BufferedReader
import java.io.InputStreamReader
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.immutable.TreeMap
import scala.collection.JavaConversions.mapAsScalaMap

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
}
