import java.util.Date

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import scala.util.matching._

object Debug {
  def printAst(a: Any): Unit = macro printAstImpl

  def printAstImpl(c: Context)(a: c.Expr[Any]) = {
    import c.universe._

    val code = show(a.tree)
    val ast  = PrettyPrint.prettify(showRaw(a.tree))

    q""" println("=====\n" + $code + "\n-----\n" + $ast) """
  }
}
