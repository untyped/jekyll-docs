---
layout: page
title:  Hello Macros
---

## What is a macro?

TODO:

 - It's a piece of code that generates code!
 - Macros mean different things in different languages:
    - Text macros versus AST transformations
    - Hygienic versus non-hygienic
    - Typed versus untyped
 - Macros mean different things **in Scala**:
    - Research work at EPFL
    - Eugene
    - Mentions of def macros, type macros, macro annotations, and so on...
    - Only def macros in this talk:
       - introduced Scala 2.10
       - improved in Scala 2.11

### Macros versus Methods

Consider the following *hello world* application that prints a greeting message and the current time:

~~~ scala
// Library code -----------------------

import java.util.Date

object LibraryMethods {
  def greeting(message: String): String = {
    val now = new Date().toString
    message + "\nThe current time is " + now
  }
}

// Main application -------------------

object HelloWorld extends App {
  println(LibraryMethods.greeting("Hello!"))
}
~~~

This code uses a library method called `greeting` that accepts a `String` argument and returns a greeting `String` involving the message and the current time. Each time we run our code we make a new call to `greeting` and get a new printed message:

~~~ bash
$ sbt helloApp/run
Hello! The current time is Thu May 15 13:15:06 BST 2014

$ sbt helloApp/run
Hello! The current time is Thu May 15 13:15:11 BST 2014
~~~

To illustrate the difference between a regular method call and a `def` macro let's re-implement `greeting` as a macro:

~~~ scala
// Library code -----------------------

import java.util.Date

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

object LibraryMacros {
  def greeting(message: String): String =
    macro greetingMacro

  def greetingMacro(c: Context)(message: c.Expr[String]) = {
    val now = new Date().toString
    q"""
     $message + "\nThis code was compiled at " + $now
     """
  }
}

// Main application -------------------

object HelloMacros extends App {
  println(LibraryMacros.greeting("Hello!"))
}
~~~

In this version of the application, `greeting` is a macro that accepts an argument of type `Expr[String]` -- a *Scala expression that evaluates to a String* -- and returns a value of type `Expr[String]`.

Unlike a regular method call that gets evaluated at run-time, our macro is evaluated when we *compile* our code. The macro generates an expression that calculates the final greeting message when the application is run. Our use-case in `HelloMacros` ends up getting compiled like:

~~~ scala
object HelloMacros extends App {
  println("Hello!" + "\nThis code was compiled at " + "Thu May 15 13:15:16 BST 2014")
}
~~~

Notice that the final code doesn't include any references to `java.util.Date` -- the `Date` object is created and converted to a `String` at compile time. This means we always get the same message when we run the application...

~~~ scala
$ sbt helloApp/run
Hello! This code was compiled at Thu May 15 13:15:16 BST 2014

$ sbt helloApp/run
Hello! This code was compiled at Thu May 15 13:15:16 BST 2014
~~~

...unless we recompile our code. If we do this, our macro gets re-run and the final application ends up containing a new timestamp:

~~~ scala
$ sbt helloApp/clean helloApp/compile
# ... output from the compiler ...

$ sbt helloApp/run
Hello! This code was compiled at Thu May 15 13:15:27 BST 2014
~~~

## What are macros good for?

Eugene Burmako's Scalapeno 2013 talk:

 - Code generation
 - Static checking
 - DSLs
