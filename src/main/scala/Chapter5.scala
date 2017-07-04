
object Main5 extends App {
  val ch5 = new Chapter5
  val counter = new ch5.Counter
  println("counter initialized to (Int.MaxValue - 1)")
  counter.increment
  println("counter incremented by 1. Current value is " + counter.current)
  counter.increment
  println("Attempted to increment counter by 1 again. But current value is " + counter.current)
  val account = new ch5.BankAccount(1000.01)
  println("BankAccount opened with a balance of 1000.01")
  account.deposit(11.3)
  println("An amount of 11.3 deposited to the BankAccount")
  account.withdraw(4.9)
  println("An amount of 4.9 withdrawn from the BankAccount")
  println("Balance amount of the BankAccount is now " + account.checkBalance)
  account.withdraw(1214.9)
  println("A  withdrawal amount of 1214.9 from the BankAccount is attempted")
  println("Balance amount of the BankAccount is now " + account.checkBalance)
}

class Chapter5 {
  class Counter {
    private var value = Int.MaxValue - 1
    def increment {
      if (value != Int.MaxValue) { // Avoiding overflow.
        value += 1
      }
    }
    def current = value
  }

  class BankAccount(private var balance: Double) {
    def deposit(amount: Double) {
      balance += amount
    }

    def withdraw(amount: Double) {
      if (balance > amount)
        balance -= amount
    }

    def checkBalance = balance
  }
}
