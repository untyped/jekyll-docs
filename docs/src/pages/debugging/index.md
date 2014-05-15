---
layout: page
title:  Debugging Macros
---

Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci, labore, quas, excepturi rem nisi necessitatibus optio fugiat facere placeat voluptate deserunt laborum distinctio repudiandae ipsa nemo atque sed voluptatem non.

`desugar` macro from [macrocosm](https://github.com/retronym/macrocosm):

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
