package berlin.bbdc.inet.flink.netflow.flow

case class Flow(srcIp: String, dstIp: String, packetCount: Long, byteCount: Long, srcPort: Int, dstPort: Int)