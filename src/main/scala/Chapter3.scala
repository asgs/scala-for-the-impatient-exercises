import java.util.TimeZone
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.SystemFlavorMap

import scala.collection.immutable.StringOps
import scala.language.postfixOps
import scala.collection.immutable.IndexedSeq
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer
import scala.util.Random
import scala.collection.JavaConversions.asScalaBuffer

object Main3 extends App {
  val ch3 = new Chapter3;
  println(ch3.generateArrayRandomIntegersUptoN(10).mkString(""));
  val a = (0 until 9).toArray
  println("Before swapping " + a.mkString(", "))
  ch3.swapConsecutiveElements(a)
  println("After swapping " + a.mkString(", "))
  val b = (0 until 9).toArray
  println("Before swapping " + b.mkString(", "))
  val c = ch3.swapConsecutiveElementsAndReturnResult(b).toArray
  println("After swapping " + c.mkString(", "))
  val avg = ch3.averageOfDoubleArray(c.map(_ * 1.0))
  println("Average of array " + avg)
  val arrayWithMixedSignedIntegers =
    Array[Int](1, 2, -1, 2, 0, -4, 5, 5, -9, -10, 0)
  println(
    "Array split into Postive And Negative portions - " + ch3
      .splitArrayIntoPostiveAndNegativePortions(arrayWithMixedSignedIntegers)
      .mkString(", "))
  println(
    "Reverse sorted array - " + ch3
      .reverseSort(ch3.generateArrayRandomIntegersUptoN(20))
      .mkString(", "))
  println(
    "Reverse sorted array buffer - " + ch3.reverseSort(
      ch3.generateArrayRandomIntegersUptoN(20).toBuffer))
  println(
    "Array with duplicate elements removed - " + ch3
      .returnArrayWithDuplicatesRemoved(
        ch3.generateArrayRandomIntegersUptoN(10))
      .mkString(", "))
  val ab2 = ArrayBuffer[Int](1, 0, -2, 3, 1, -4, -5, 7)
  println("Removing all but first Negative integer from " + ab2)
  ch3.removeAllButFirstNegativeInteger(ab2)
  println(" would become " + ab2)
  println(
    "TimeZone IDs with continent stripped and sorted - " + ch3.generateSortedAvailableJavaTimeZoneIdsWithContinentStripped
      .mkString(", "))
  println(
    "Native Image Flavor list as Scala Buffer - " + ch3.getNativeSystemImageFlavorsAsBuffer)
}

class Chapter3 {
  def generateArrayRandomIntegersUptoN(n: Int): Array[Int] = {
    val random = new Random()
    val array = new Array[Int](n)
    for (i <- -1 until (n - 1))
      array(i + 1) = random.nextInt(n)
    array
  }

  def swapConsecutiveElements(a: Array[Int]) {
    for (i <- 0 until (a.length, 2))
      if ((i + 1) < a.length) {
        var temp = a(i)
        a(i) = a(i + 1)
        a(i + 1) = temp
      }
  }

  def swapConsecutiveElementsAndReturnResult(a: Array[Int]): IndexedSeq[Int] = {
    for (i <- 0 until a.length)
      yield {
        if ((i + 1) < a.length) {
          if ((i % 2) != 0) {
            a(i - 1)
          } else {
            a(i + 1)
          }
        } else {
          if (a.length % 2 == 0) {
            a(i - 1)
          } else {
            a(i)
          }
        }
      }
  }

  def splitArrayIntoPostiveAndNegativePortions(a: Array[Int]): Array[Int] = {
    val ab = new ArrayBuffer[Int]
    var index = 0;
    for (element <- a) {
      if (element > 0) {
        ab.insert(index, element)
        index += 1
      } else {
        ab.append(element)
      }
    }
    ab.toArray
  }

  def averageOfDoubleArray(x: Array[Double]): Double = {
    x.toStream.sum / x.size
  }

  def reverseSort(a: Array[Int]): Array[Int] = {
    a.sortWith(_ > _) // or a.sorted.reverse
  }

  def reverseSort(a: Buffer[Int]): Buffer[Int] = {
    a.sortWith(_ > _) // or a.sorted.reverse
  }

  def returnArrayWithDuplicatesRemoved(a: Array[Int]): Array[Int] = {
    a.distinct
  }

  def generateSortedAvailableJavaTimeZoneIdsWithContinentStripped()
    : Array[String] = {
    TimeZone.getAvailableIDs
      .map(s => s.substring(s.lastIndexOf("/") + 1))
      .sorted
  }

  def removeAllButFirstNegativeInteger(ab1: ArrayBuffer[Int]) {
    val negativeIndicesSeq: IndexedSeq[Int] =
      for (i <- 0 until ab1.length if ab1(i) < 0)
        yield i
    val negativeIndicesSeqReversed = negativeIndicesSeq.reverse
    val negativeIndicesSeqWithFirstIndexRemoved =
      negativeIndicesSeqReversed.dropRight(1)
    for (i <- 0 until negativeIndicesSeqWithFirstIndexRemoved.length)
      ab1.remove(negativeIndicesSeqWithFirstIndexRemoved(i))
  }

  def getNativeSystemImageFlavorsAsBuffer(): Buffer[String] = {
    val flavors =
      SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  }

}
