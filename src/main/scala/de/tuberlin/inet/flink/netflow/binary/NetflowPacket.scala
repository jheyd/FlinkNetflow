package de.tuberlin.inet.flink.netflow.binary

case class NetflowPacket(header: NetflowHeader, records: Seq[NetflowRecord])
