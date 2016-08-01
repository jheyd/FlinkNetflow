package de.tuberlin.inet.flink.netflow



case class Flow(srcIp: String, dstIp: String, packetCount: Long, byteCount: Long, srcPort: Int, dstPort: Int)