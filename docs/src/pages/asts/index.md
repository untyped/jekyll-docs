---
layout: page
title:  Constructing ASTs
---

Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci, labore, quas, excepturi rem nisi necessitatibus optio fugiat facere placeat voluptate deserunt laborum distinctio repudiandae ipsa nemo atque sed voluptatem non.

TODO:

 - desugar macro from macrocosm
 - macrocosm here https://github.com/retronym/macrocosm
 - mentioned here http://www.scala-sbt.org/0.13.2/docs/Detailed-Topics/Macro-Projects.html

~~~ scala
package demo

import language.experimental.macros
import scala.reflect.macros.Context

object Demo {

  // Returns the tree of `a` after the typer, printed as source code.
  def desugar(a: Any): String = macro desugarImpl

  def desugarImpl(c: Context)(a: c.Expr[Any]) = {
    import c.universe._

    val s = show(a.tree)
    c.Expr(
      Literal(Constant(s))
    )
  }
}
~~~