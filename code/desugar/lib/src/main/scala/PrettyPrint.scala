import scala.util.parsing.combinator._

object PrettyPrint extends JavaTokenParsers {
  override def skipWhitespace = true

  sealed trait Node {
    def prettyPrint(indent: Int): String
  }

  final case class Func(name: String, args: Seq[Node]) extends Node {
    def prettyPrint(indent: Int): String =
      (" " * indent) + name + (args match {
        case Seq()                            =>  "()"
        case Seq(Term(text))                  => s"($text)"
        case Seq(Func(name, Seq(Term(text)))) => s"($name($text))"
        case args => args
          .map(_.prettyPrint(indent + 2))
          .mkString("(\n", ",\n", "\n" + (" " * indent) + ")")
      })
  }

  final case class Term(text: String) extends Node {
    def prettyPrint(indent: Int): String = (" " * indent) + text
  }

  def node: Parser[Node] =
    func | term

  def func: Parser[Func] =
    ident ~ "(" ~ repsep(node, ",") ~ ")" ^^ {
      case name ~ _ ~ args ~ _ =>
        Func(name, args)
    }

  def term: Parser[Term] =
    (ident | wholeNumber | decimalNumber | stringLiteral | floatingPointNumber | "()") ^^ {
      case term =>
        Term(term.toString)
    }

  def prettify(in: String) = {
    parseAll(node, in).map(_.prettyPrint(0)).getOrElse(in)
  }

}