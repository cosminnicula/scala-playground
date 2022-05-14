import org.scalatest.funsuite.AnyFunSuite

// https://docs.scala-lang.org/tour/tuples.html
class TuplesTests extends AnyFunSuite {
  val t = ("one", 2) // inferred type is (String, Int)
  val elements = List(
    ("Nytrogen", 14.007), ("Xenon", 131.29), ("Argon", 39.948)
  )

  test("access tuple elements") {

    assert(t._1 == "one")
    assert(t._2 == 2)
  }

  test("pattern matching 1") {
    val (first, second) = t

    assert(first == "one")
    assert(second == 2)
  }

  test("pattern matching 2") {
    var nytrogenAtomicNumber = -1.0

    elements.foreach({
      case ("Nytrogen", atomicNumber) => {
        nytrogenAtomicNumber = atomicNumber
      }
      case _ =>
    })

    assert(nytrogenAtomicNumber > -1)
  }

  test("pattern matching 3") {
    var nytrogenAtomicNumber = -1.0

    for ((name, atomicNumber) <- elements) {
      if (name == "Nytrogen") {
        nytrogenAtomicNumber = atomicNumber
      }
    }

    assert(nytrogenAtomicNumber > -1)
  }
}