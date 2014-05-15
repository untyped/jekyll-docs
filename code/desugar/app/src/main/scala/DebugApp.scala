object DebugApp extends App {
  import Debug._

  printAst {
    123
  }

  printAst {
    "abc"
  }

  printAst {
    123 + 234
  }

  printAst {
    123.toString
  }

  def sum(a: Int, b: Int) =
    a + b

  printAst {
    sum(123, 234)
  }

  printAst {
    123 ; 234
  }

  printAst {
    val a = 123
  }

  printAst {
    val a = 123
    val b = 234
    a + b
  }

}
