package de.tuberlin.inet.flink.netflow

import java.nio.ByteBuffer

class BinaryData(bytes: Seq[Byte]) {

  def extractUnsignedShort(offset: Int): Int = ByteBuffer.wrap(Array[Byte](0, 0) ++ bytes.slice(offset, offset + 2)).getInt()

  def extractUnsignedInt(offset: Int): Long = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ bytes.slice(offset, offset + 4)).getLong()

  def extractBytes(offset: Int, count: Int): Array[Byte] = bytes.slice(offset, offset + count).toArray
}

object BinaryData {
  def apply(bytes: Seq[Byte]): BinaryData = new BinaryData(bytes)
}
