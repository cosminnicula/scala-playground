package util

object Log {
  def info(message: String): String = {
    println(s"INFO: $message")
    message
  }
}

class Util1 {
  object Log {
    def info(message: String): String = {
      println(s"INFO: $message")
      message
    }
  }
}

class Util2 {
  object Log {
    def info(message: String): String = {
      println(s"INFO: $message")
      message
    }
  }
}