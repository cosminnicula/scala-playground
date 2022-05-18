import org.scalatest.funsuite.AnyFunSuite

//--------

trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to
  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}

//--------

// has an abstract field model gets implemented by Laptop and DesktopPC in their constructors
trait Computer {
  val model: String
}

class Laptop(val model: String) extends Computer
class DesktopPC(val model: String) extends Computer

//--------

// https://docs.scala-lang.org/tour/traits.html

// Traits cannot be instantiated, and therefore have no parameters
class Traits extends AnyFunSuite{

  test("class extending single trait") {
    val iterator = new IntIterator(10)

    assert(iterator.next() == 0)
    assert(iterator.next() == 1)
  }

  test("subtyping") {
    val laptop = new Laptop("Apple")
    val desktopPc = new DesktopPC("Dell")

    val computers = List(laptop, desktopPc)

    assert(computers(0).model.equals("Apple"))
    assert(computers(1).model.equals("Dell"))
  }
}


