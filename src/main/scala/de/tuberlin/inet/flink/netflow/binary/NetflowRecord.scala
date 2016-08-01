package de.tuberlin.inet.flink.netflow.binary

import java.net.InetAddress

import de.tuberlin.inet.flink.netflow.binary.util.BinaryData
import de.tuberlin.inet.flink.netflow.flow.Flow

case class NetflowRecord(data: BinaryData) {
  def toFlow: Flow = {

    val srcaddr = InetAddress.getByAddress(data.extractBytes(0, 4)).getHostAddress
    val dstaddr = InetAddress.getByAddress(data.extractBytes(4, 4)).getHostAddress

    val dPkts = data.extractUnsignedInt(16)
    val dOctets = data.extractUnsignedInt(20)

    val srcport = data.extractUnsignedShort(32)
    val dstport = data.extractUnsignedShort(34)

    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

}