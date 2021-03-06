package berlin.bbdc.inet.flink.netflow.binary.util

import java.nio.ByteBuffer

case class BinaryData(bytes: Seq[Byte]) {

  def extractUnsignedShort(offset: Int): Int = ByteBuffer.wrap(Array[Byte](0, 0) ++ bytes.slice(offset, offset + 2)).getInt()

  def extractUnsignedInt(offset: Int): Long = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ bytes.slice(offset, offset + 4)).getLong()

  def extractBytes(offset: Int, count: Int): Array[Byte] = bytes.slice(offset, offset + count).toArray
}