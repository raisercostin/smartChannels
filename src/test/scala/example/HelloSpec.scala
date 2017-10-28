package example

import org.scalatest._
import org.raisercostin.smart.channels.ScmReader
import org.raisercostin.jedi.Locations

class HelloSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    val channels = ScmReader.readChannels(Locations.classpath("channel_list_UE48H8000_1401.scm"))
  }
}
