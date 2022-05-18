import org.scalatest.funsuite.AnyFunSuite

class BasicUser {}

// when class fields are declared with var, Scala automatically generates getters and setters
class User(var domain: String = "local", var username: String = "user", var isLocked: Boolean = false) {
  def getFullUsername(): String = {
    s"$domain/$username"
  }

  override def toString: String =
    s"$domain, $username, $isLocked"
}

// when class fields are declared with val, Scala automatically generates getters, but not setters
class UserWithPrivateMembers(val domain: String = "local", val username: String = "user", val isLocked: Boolean = false) {}

// when class fields are declared without val or var, those members are not publicly accessible, and only available inside the class
class UserWithPrivateMembers2(domain: String = "local", username: String = "user", isLocked: Boolean = false) {
  def getFullUsername(): String = {
    s"$domain/$username"
  }
}

class UserWithCustomGettersAndSetters {
  private var _domain = "local"
  private var _username = "user"
  private var _isLocked = false

  // ugly limitation forcing you to differentiate the name of the private variables from the name of the method (e.g. you
  // need to have something like "_domain" instead of directly having a "domain" private variable, because of naming
  // collision with the name of the method
  def domain: String = _domain

  // notice _= for the setter
  def domain_= (newDomain: String): Unit = {
    _domain = newDomain
  }

  def username: String = _username

  def username_= (newUsername: String): Unit = {
    _username = newUsername
  }

  def isLocked: Boolean = _isLocked

  def isLocked_= (newIsLocked: Boolean): Unit = {
    _isLocked = newIsLocked
  }
}

// https://docs.scala-lang.org/tour/classes.html
class Classes extends AnyFunSuite {
  test("basic class with default constructor and no body") {
    val basicUser = new BasicUser() // can omit parentheses: new User

    assert(basicUser != null)
  }

  test("class with constructor, body, automatic getters and setters") {
    val user = new User("domain1", "someUser", false)

    assert(user.domain.equals("domain1"));
    assert(user.username.equals("someUser"));
    assert(user.isLocked.equals(false));

    assert(user.getFullUsername().equals("domain1/someUser"))
    assert(user.toString.equals("domain1, someUser, false"))

    user.domain = "domain2"
    assert(user.domain.equals("domain2"));
  }

  test("class default parameters") {
    val user = new User

    assert(user.domain.equals("local"));
    assert(user.username.equals("user"));
    assert(user.isLocked.equals(false));
  }

  test("class with private class fields declared with val") {
    val user = new UserWithPrivateMembers

    assert(user.domain.equals("local"));
    // however, you cannot change the domain, because it has no setter: user.domain = "domain1"
  }

  test("class with private class fields declared without val or var") {
    val user = new UserWithPrivateMembers2

    assert(user.getFullUsername().equals("local/user"))
    // however, you don't have any getter or setter: user.domain or user.domain = "domain1" won't compile
  }

  test("class with custom getters and setters") {
    val user = new UserWithCustomGettersAndSetters

    user.domain = "domain1"
    user.username = "user1"
    user.isLocked = true

    assert(user.domain.equals("domain1"));
    assert(user.username.equals("user1"));
    assert(user.isLocked.equals(true));
  }

  test("named arguments") {
    val user = new User(domain = "somedomain", isLocked = false, username = "someuser")

    assert(user.domain.equals("somedomain"));
    assert(user.username.equals("someuser"));
    assert(user.isLocked.equals(false));
  }
}