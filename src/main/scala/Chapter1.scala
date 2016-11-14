object Main extends App
{
  new Chapter1().printAnswers
}

class Chapter1 {
  def printAnswers()  {
    println("1. Typing 3. followed by Tab key results in suggesting methods from scala.math.BigInt or Int class.");
    println("2. Running import math._, sqrt(3), pow(resvar, 2) doesn't exactly yield 3 which is expected because of floating-point calculations.");
    println("3. The res variables are val. Any operation on a res variable results in a new res var created.");
    println("4. \"crazy\" * 3 will create a string concatenated with crazy three times. It can be found on the StringOps Scaladoc.");
    println("5. 10 max 2 returns the maximum of this Int and the Int passed in as the parameter. It can be found on the Int Scaladoc.");
    println("6. val bigInt : BigInt = 2; bigInt.pow(1024)");
    println("7. probablePrime is a function of BigInt Scala object.");
    println("8. A random alphanumeric string generated using BigInt with BigInt.probablePrime(200, util.Random).toString(36) is "  + BigInt.probablePrime(200, util.Random).toString(36));
    println("9. val string = \"Hello, World!\"; string(0); string(string.length - 1);");
    println("10. take takes first n elements and drop drops first n elements, while takeRight and dropRight deal with the last n elements. Not sure how these functions compare to substring. Need more investigation.")
  }
}
