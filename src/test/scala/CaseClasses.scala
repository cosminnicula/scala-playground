import org.scalatest.funsuite.AnyFunSuite

case class Car(model: String)

// https://docs.scala-lang.org/tour/case-classes.html
class CaseClasses extends AnyFunSuite {
  test("simple case class") {
    val car = Car("Tesla")

    assert(car.model.equals("Tesla"))

    // by default, the class fields are immutable (val), which means you cannot reassign values to then.
    // For example car.model = "Volkswagen" won't compile
    // you can make class fields mutable with var, but is not recommended
  }
}
