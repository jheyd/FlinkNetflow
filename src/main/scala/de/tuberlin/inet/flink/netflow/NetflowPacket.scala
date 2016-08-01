package de.tuberlin.inet.flink.netflow


case class NetflowPacket(header: NetflowHeader, records: Seq[NetflowRecord])
