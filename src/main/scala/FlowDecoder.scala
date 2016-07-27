import java.net.InetAddress
import java.nio.ByteBuffer

import org.codehaus.jackson.util.BufferRecycler.ByteBufferType

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val srcaddr = InetAddress.getByAddress(record.bytes.slice(0, 4).toArray).getHostAddress
    val dstaddr = InetAddress.getByAddress(record.bytes.slice(4, 8).toArray).getHostAddress
    val dPkts = ByteBuffer.wrap(record.bytes.slice(16, 20).toArray).getInt
    val dOctets = ByteBuffer.wrap(record.bytes.slice(20, 24).toArray).getInt
    new Flow(srcaddr, dstaddr, dPkts, dOctets)
  }

}
