import java.util.Date

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

object LibraryMacros {
  def greeting(message: String): String =
    macro greetingMacro

  def greetingMacro(c: Context)(message: c.Expr[String]): c.Expr[String] = {
    val now = new Date().toString
    q"""
     $message + "\nThis code was compiled at " + $now
     """
  }
}
