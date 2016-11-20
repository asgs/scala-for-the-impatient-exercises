object Main2 extends App {
  val chapter2 = new Chapter2
  println((chapter2 signum(2)) + ", " + (chapter2 signum(-15)) + ", " + (chapter2 signum(0)))
  println("The empty block expression {} has no value. Its type is Unit.")
  chapter2 simpleForLoopCountingFromTenToZero;
  println("The assignment x = y = 1 is not valid for any type of x nor y. Because Assignments in Scala don't have a type. Actually, it's Unit. So assigning 1 to y doesn't mean it's assigned to x as well, like it's done in C/C++/Java.")
  chapter2 countdown(9)
  println()
  println(chapter2 stringUnicodeProductComputer("Hello"))
}

class Chapter2 {
  def signum(i : Int) : Int = if (i > 0) 1 else if (i < 0) -1 else 0
  def simpleForLoopCountingFromTenToZero = for (i <- 1 to 11) print((11 - i) + ",")
  def countdown(n : Int) = for (i <- 0 to n) print(n - i)
  def stringUnicodeProductComputer(string : String) : Long = {var product = 1L; for (unicode <- string.codePoints.toArray) { product = product * unicode}; return product;}
}
