package org.raisercostin.smart.channels

import org.raisercostin.jedi.InputLocation
import com.univocity.parsers.fixed.FixedWidthFields
import com.univocity.parsers.fixed.FixedWidthParserSettings
import com.univocity.parsers.fixed.FixedWidthParser
import com.univocity.parsers.common.record.Record

trait ScmChannel
object ScmReader {
  def readChannels(src: InputLocation):ScmChannel = {
    val channelsFile = src.unzip.child("map-CableD")
    println(channelsFile.length)
    
    // creates the sequence of field lengths in the file to be parsed
    val lengths = new FixedWidthFields("""2
2
2
1
1
1
1
2
1
1
1
1
1
1
1
200
1
1
1
""".stripMargin.split("\n").map(_.toInt):_*);

    // creates the default settings for a fixed width parser
    val fields = new FixedWidthFields()
    fields.addField("Channel number", 2)
    fields.addField("VPID", 2)
    fields.addField("MPID (PCR PID)", 2)
    fields.addField("unknown1", 4)
    fields.addField("Symbol rate (ksym/s)", 2)
    fields.addField("unknown2", 4)
    fields.addField("Network", 1)
    fields.addField("unknown3", 2)
    fields.addField("Channel name (100 chars unicode)", 200)
    fields.addField("unknown4", 3)

    //sets the character used for padding unwritten spaces in the file
    //settings.getFormat().setPadding('_');

    //the file used in the example uses '\n' as the line separator sequence.
    //the line separator sequence is defined here to ensure systems such as MacOS and Windows
    //are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
    //settings.getFormat().setLineSeparator("\n");

    // creates a fixed-width parser with the given settings
    val parser = new FixedWidthParser(new FixedWidthParserSettings(fields))

    // parses all rows in one go.
    import scala.collection.JavaConverters._
    val allRows:Seq[Record] = channelsFile.usingReader(reader=>parser.parseAllRecords(reader).asScala)
    println(allRows.mkString("\n"))
    ???
  }
}