import org.scalatest.funsuite.AnyFunSuite

// https://www.baeldung.com/scala/currying
class Curry extends AnyFunSuite{
  test("curried functions") {
    val sum: (Int, Int) => Int = (x, y) => x + y
    val curriedSum: Int => Int => Int = x => y => x + y

    assert(sum(10, 15) == 25)
    assert(curriedSum(10)(15) == 25)
  }

  test("curried methods") {
    def sum(x: Int, y: Int): Int = x + y
    def curriedSum(x: Int)(y: Int): Int = x + y

    assert(sum(10, 15) == 25)
    assert(curriedSum(10)(15) == 25)
  }

  test("convert method with multiple arguments to curried function") {
    def sum(x: Int, y: Int): Int = x + y
    val curriedSum: Int => Int => Int = (sum _).curried

    assert(sum(10, 15) == 25)
    assert(curriedSum(10)(15) == 25)
  }

  test("type inference") {
    def find[A](xs: List[A], predicate: A => Boolean): Option[A] = {
      xs match {
        case Nil => None
        case head :: tail =>
          if (predicate(head)) Some(head) else find(tail, predicate)
      }
    }

    assert(find(List(1, 2, 3), (x: Int) => x % 2 == 0) == Some(2))
  }
}
