import scala.collection.immutable.StringOps
import scala.language.postfixOps

object Main2 extends App {
  val chapter2 = new Chapter2
  println("Demonstration of signum of 2, -15 and 0; " + (chapter2 signum(2)) + ", " + (chapter2 signum(-15)) + ", " + (chapter2 signum(0)))
  println("The empty block expression {} has no value. Its type is Unit.")
  print("Executing for loop to count from 10 to 0; ");
  chapter2 simpleForLoopCountingFromTenToZero;
  println("The assignment x = y = 1 is not valid for any type of x nor y. Because Assignments in Scala don't have a type. Actually, it's Unit. So assigning 1 to y doesn't mean it's assigned to x as well, like it's done in C/C++/Java.")
  print("Executing for loop to count down from n (9); ");
  chapter2.countdown(9)
  println("Unicode product of \"Hello\" using a for-loop is " + (chapter2 stringUnicodeProductComputer("Hello")))
  println("Unicode product of \"Hello\" using StringOps is " + (chapter2 stringUnicodeProductComputerWithStringOps("Hello")))
  println("Unicode product of \"Hello\" using Recursion is " + (chapter2 stringUnicodeProductComputerRecursively("Hello")))
  println("Power(10, 4) is " + (chapter2 computeXPowerNRecursively(10, 4)))
  println("Power(10, 2) is " + (chapter2 computeXPowerNRecursively(10, 2)))
  println("Power(10, 0) is " + (chapter2 computeXPowerNRecursively(10, 0)))
  println("Power(10, -2) is " + (chapter2 computeXPowerNRecursively(10, -2)))
}

class Chapter2 {

  def signum(i : Int) : Int = if (i > 0) 1 else if (i < 0) -1 else 0

  def simpleForLoopCountingFromTenToZero = {
    for (i <- 1 to 11) {
      print((11 - i) + ",")
    };
    println()
  }

  def countdown(n : Int) = {
    for (i <- 0 to n) {
      print(n - i)
    };
    println()
  }

  def stringUnicodeProductComputer(string : String) : Long = {
    var product = 1L;
    for (unicode <- string.codePoints.toArray) {
      product = product * unicode
    };
    product
  }

  def stringUnicodeProductComputerRecursively(string : String) : Long = {
    if (string.length() == 1) {
      string.codePointAt(0);
    } else {
      string.codePointAt(0) * stringUnicodeProductComputerRecursively(string.substring(1))
    }
  }

  def stringUnicodeProductComputerWithStringOps(string : String) : Long = new StringOps(string).map(_.toLong).product

  def computeXPowerNRecursively(x : Int, n : Int) : Double = {
    var result = 1L;
    if (n == 0) {
      result
    } else if (n > 0 && n % 2 != 0) {
      x * computeXPowerNRecursively(x, n - 1)
    } else if (n > 0 && n % 2 == 0) {
      computeXPowerNRecursively(x, n / 2) * computeXPowerNRecursively(x, n / 2)
    } else {
      1 / computeXPowerNRecursively(x, -n)
    }
  }
}
