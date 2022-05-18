import org.scalatest.funsuite.AnyFunSuite

case class Car(model: String, color: String, options: Seq[String])

// https://docs.scala-lang.org/tour/case-classes.html
class CaseClasses extends AnyFunSuite {
  test("simple case class") {
    // case classes don't require "new" for instantiation; it has an internal "apply" method which takes care of the
    // object construction
    val car = Car("Tesla", "black", Seq("heated steering wheel", "heated mirrors"))

    assert(car.model.equals("Tesla"))

    // by default, the class fields are immutable (val), which means you cannot reassign values to then.
    // For example car.model = "Volkswagen" won't compile
    // you can make class fields mutable with var, but is not recommended
  }

  test("compare by structure, not by reference") {
    val car1 = Car("Tesla", "black", Seq("heated steering wheel", "heated mirrors"))
    val car2 = Car("Tesla", "black", Seq("heated steering wheel", "heated mirrors"))

    assert(car1 == car2)
  }

  test("shallow copy") {
    val car1 = Car("Tesla", "black", Seq("heated steering wheel", "heated mirrors"))
    val car2 = car1.copy()

    assert(car1 == car2)
  }
}
