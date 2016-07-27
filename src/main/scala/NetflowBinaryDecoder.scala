import java.io.{File, FileInputStream}
import java.nio.ByteBuffer

import scala.collection.mutable

object NetflowBinaryDecoder {
  val headerLength = 24
  val flowSize = 48

  def load(inputFile: File, maxChunks: Int): Seq[NetflowPacket] = load(inputFile, Some(maxChunks))

  def load(inputFile: File): Seq[NetflowPacket] = load(inputFile, None)

  private def load(inputFile: File, maxChunks: Option[Int]): Seq[NetflowPacket] = {
    val in = new FileInputStream(inputFile)
    var i = 0
    val packets = mutable.Buffer[NetflowPacket]()
    val headerBytes = new Array[Byte](headerLength)
    while (maxChunks.forall(i < _) && in.read(headerBytes) == headerLength) {
      val packetCountBuffer = ByteBuffer.wrap(Array[Byte](0, 0, headerBytes(2), headerBytes(3)))
      val packetCount = packetCountBuffer.getInt()

      val records = mutable.Buffer[NetflowRecord]()
      val buf = new Array[Byte](flowSize)
      var k = 0
      while (k < packetCount && in.read(buf) == flowSize) {
        records += new NetflowRecord(buf.clone())
        k += 1
      }

      packets += new NetflowPacket(new NetflowHeader(headerBytes.clone()), records.toSeq)
      i += 1
    }
    packets
  }

}
