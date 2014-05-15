import java.util.Date

object LibraryMethods {
  def greeting(message: String): String = {
    val now = new Date().toString
    message + "\nThe current time is " + now
  }
}
