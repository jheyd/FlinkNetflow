package berlin.bbdc.inet.flink.netflow.binary

case class NetflowPacket(header: NetflowHeader, records: Seq[NetflowRecord])
