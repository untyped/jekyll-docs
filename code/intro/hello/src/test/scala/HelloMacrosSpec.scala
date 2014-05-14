import org.specs2.mutable._

class HelloMacrosSpec extends Specification {
  "HelloMacros.compileInfo" should {
    "always return the same compile info" in {
      // Once compiled, this will always print the same message (every time you run it):
      println(HelloMacros.compileInfo)

      HelloMacros.compileInfo mustEqual HelloMacros.compileInfo
    }
  }
}