import java.net.InetAddress

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val srcIp = InetAddress.getByAddress(record.bytes.slice(0, 4).toArray).getHostAddress
    val dstIp = InetAddress.getByAddress(record.bytes.slice(4, 8).toArray).getHostAddress
    new Flow(srcIp, dstIp)
  }

}
