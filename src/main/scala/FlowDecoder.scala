import java.net.InetAddress
import java.nio.ByteBuffer

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val srcaddr = InetAddress.getByAddress(record.bytes.slice(0, 4).toArray).getHostAddress
    val dstaddr = InetAddress.getByAddress(record.bytes.slice(4, 8).toArray).getHostAddress
    val dPkts = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ record.bytes.slice(16, 20)).getLong
    val dOctets = ByteBuffer.wrap(Array[Byte](0, 0, 0, 0) ++ record.bytes.slice(20, 24)).getLong
    val srcport = ByteBuffer.wrap(Array[Byte](0, 0) ++ record.bytes.slice(32, 34)).getInt()
    val dstport = ByteBuffer.wrap(Array[Byte](0, 0) ++ record.bytes.slice(32, 34)).getInt()
    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

}
