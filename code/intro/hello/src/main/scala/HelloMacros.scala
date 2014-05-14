import java.util.Date
import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

class CompileInfo(val c: Context) {
  import c.universe._

  val now = new Date().toString

  def impl = {
    val msg = s"Hello! This code was compiled at $now."
    c.literal(msg)
  }
}

object HelloMacros {
  def compileInfo: String = macro CompileInfo.impl
}
