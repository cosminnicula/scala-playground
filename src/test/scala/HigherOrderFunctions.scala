import org.scalatest.funsuite.AnyFunSuite

// https://docs.scala-lang.org/tour/higher-order-functions.html
// https://www.baeldung.com/scala/functions-methods
class HigherOrderFunctions extends AnyFunSuite {

  test("named function") {
    val addOne = (num: Int) => num + 1
    val numbers = Seq(1, 2, 3, 4, 5)
    val newNumbers = numbers.map(addOne)

    assert(newNumbers == Seq(2, 3, 4, 5, 6))
  }

  test("anonymous function") {
    val numbers = Seq(1, 2, 3, 4, 5)
    val newNumbers = numbers.map((num: Int) => num + 1)

    assert(newNumbers == Seq(2, 3, 4, 5, 6))
  }

  test("pass ugly _") {
    val numbers = Seq(1, 2, 3, 4, 5)
    val newNumbers = numbers.map(_ + 1)

    assert(newNumbers == Seq(2, 3, 4, 5, 6))
  }

  test("method instead of function") {
    case class NumberIncrementer(numbers: Seq[Int]) {
      private def addOne(num: Int) = num + 1
      def incrementNumbers: Seq[Int] = numbers.map(addOne)
    }

    val incrementer:NumberIncrementer = NumberIncrementer(Seq(1, 2, 3, 4, 5))
    val newNumbers = incrementer.incrementNumbers // inferred type is Seq[Int]

    assert(newNumbers == Seq(2, 3, 4, 5, 6))
  }

  test ("function as parameter") {
    case class NumberIncrementer(numbers: Seq[Int]) {
      def incrementNumbers(incrementer: Int => Int): Seq[Int] = numbers.map(incrementer)
    }

    var incrementer = NumberIncrementer(Seq(1, 2, 3, 4, 5))
    var newNumbers = incrementer.incrementNumbers((num: Int) => num + 1)

    assert(newNumbers == Seq(2, 3, 4, 5, 6))

    incrementer = NumberIncrementer(Seq(1, 2, 3, 4, 5))
    newNumbers = incrementer.incrementNumbers((num: Int) => num + 2)

    assert(newNumbers == Seq(3, 4, 5, 6, 7))
  }

  // same with TypeScript curry functions https://gist.github.com/donnut/fd56232da58d25ceecf1
  test ("function returning function") {
    def incrementAndRaiseToPower(numbers: Seq[Int], incrementer: Int): (Int) => Seq[Double] = {
      val newNumbers = numbers.map((num: Int) => num + incrementer)
      (exponent: Int) => newNumbers.map((num: Int) => Math.pow(num, exponent))
    }

    val numbers = Seq(1, 2, 3, 4, 5)
    val newNumbers = incrementAndRaiseToPower(numbers, 1)(2)

    assert(newNumbers == Seq(4, 9, 16, 25, 36))
  }
}
