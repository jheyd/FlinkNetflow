import java.net.InetAddress
import java.nio.ByteBuffer

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val recordBytes = record.bytes.toArray

    val srcaddr = InetAddress.getByAddress(recordBytes.slice(0, 4)).getHostAddress
    val dstaddr = InetAddress.getByAddress(recordBytes.slice(4, 8)).getHostAddress

    val dPkts = extractUnsignedInt(recordBytes, 16)
    val dOctets = extractUnsignedInt(recordBytes, 20)

    val srcport = extractUnsignedShort(recordBytes, 32)
    val dstport = extractUnsignedShort(recordBytes, 34)

    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

  def extractUnsignedShort(recordBytes: Array[Byte], offset: Int): Int = {
    ByteBuffer.wrap(Array[Byte](0, 0) ++ recordBytes.slice(offset, offset + 2)).getInt()
  }

  def extractUnsignedInt(recordBytes: Array[Byte], offset: Int): Long = {
    ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ recordBytes.slice(offset, offset + 4)).getLong
  }
}
