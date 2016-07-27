import java.net.InetAddress

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val srcaddr = InetAddress.getByAddress(record.bytes.slice(0, 4).toArray).getHostAddress
    val dstaddr = InetAddress.getByAddress(record.bytes.slice(4, 8).toArray).getHostAddress
    new Flow(srcaddr, dstaddr)
  }

}
