package berlin.bbdc.inet.flink.netflow.binary

import berlin.bbdc.inet.flink.netflow.binary.util.BinaryData

case class NetflowHeader(data: BinaryData) {
  def packetLength: Int = data.extractUnsignedShort(2)
}