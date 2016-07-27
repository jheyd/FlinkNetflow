import java.net.InetAddress

object FlowDecoder {
  def decode(record: NetflowRecord): Flow = {
    val dstIp = InetAddress.getByAddress(record.bytes.slice(4, 8).toArray).getHostAddress
    new Flow(dstIp)
  }

}
