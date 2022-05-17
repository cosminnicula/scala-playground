import org.scalatest.funsuite.AnyFunSuite
import util.{Log, Util1, Util2}

//--------

// Circle class has a companion object Circle
case class Circle(radius: Double) {
  import Circle._
  // Note how this method has access to the private calculateArea method inside Circle object.
  // This is provided by so called "companion objects" mechanism.
  def area: Double = calculateArea(radius)
}

object Circle {
  private def calculateArea(radius: Double): Double = Math.PI * Math.pow(radius, 2.0)
}

//--------

class Email(val username: String, val domainName: String)

// Email object has a companion class Email
object Email {
  def fromString(emailString: String): Option[Email] = {
    emailString.split('@') match {
      case Array(a, b) => Some(new Email(a, b))
      case _ => None
    }
  }
}

//--------

// https://docs.scala-lang.org/tour/singleton-objects.html

// Q: Why singleton objects and not static classes like in Java?
// A: Scala is purely object oriented, and static classes are not objects, are classes, which means they don't fit into
// the object oriented mantra. In Java, a static method in the subclass with the same signature as in the superclass
// will hide that method, not override it, which breaks the object oriented principle of inheritance.
// Side note: Overriding is at runtime, whereas hiding is at compile time.

// Class and object companions needs to be defined in the same file
class SingletonObjects extends AnyFunSuite {
  test("simple singleton object") {
    val message = Log.info("something")

    assert(message.equals("something"));
  }

  test("nested singleton objects") {
    val util1 = new Util1
    val util2 = new Util2

    // Util1's Log nested singleton object instance is different from Util2's Log nested singleton object instance
    assert(util1.Log != util2.Log);
  }

  test("companion object 1 ") {
    // a companion object is an object with the same name as a class
    val c = new Circle(2)

    assert(c.area == Math.PI * Math.pow(2, 2.0))
  }

  // Scala's Some and None (from the Option class) is the same with Java 8 Optional
  test("companion object 2") {
    val validEmail = Email.fromString("scala.center@epfl.ch")

    assert(validEmail.get.domainName.equals("epfl.ch"))
    assert(validEmail.get.username.equals("scala.center"))

    val invalidEmail = Email.fromString("scala.centerepfl.ch")

    assert(invalidEmail.eq(None))

    var domainName: String = ""
    var username: String = ""

    invalidEmail match {
      case Some(email) => {
        domainName = email.domainName
        username = email.username
      }
      case None =>
    }

    assert(domainName.equals(""))
    assert(username.equals(""))
  }
}
