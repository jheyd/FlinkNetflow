import java.net.InetAddress

case class Flow(srcIp: String, dstIp: String, packetCount: Int, byteCount: Int, srcPort: Int, dstPort: Int)