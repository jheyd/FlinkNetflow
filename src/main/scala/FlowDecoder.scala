import java.net.InetAddress
import java.nio.ByteBuffer

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val recordBytes = record.bytes.toArray

    val srcaddr = InetAddress.getByAddress(recordBytes.slice(0, 4)).getHostAddress
    val dstaddr = InetAddress.getByAddress(recordBytes.slice(4, 8)).getHostAddress

    val dPkts = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ recordBytes.slice(16, 20)).getLong
    val dOctets = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ recordBytes.slice(20, 24)).getLong

    val srcport = ByteBuffer.wrap(Array[Byte](0, 0) ++ recordBytes.slice(32, 34)).getInt()
    val dstport = ByteBuffer.wrap(Array[Byte](0, 0) ++ recordBytes.slice(34, 35)).getInt()

    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

}
