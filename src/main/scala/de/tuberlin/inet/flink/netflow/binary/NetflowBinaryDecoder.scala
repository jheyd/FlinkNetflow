package de.tuberlin.inet.flink.netflow.binary

import java.io.{File, FileInputStream}

import de.tuberlin.inet.flink.netflow.binary.util.BinaryChunks

import scala.collection.mutable

object NetflowBinaryDecoder {
  val headerLength = 24
  val flowSize = 48

  def load(inputFile: File, maxChunks: Int): Seq[NetflowPacket] = load(inputFile, Some(maxChunks))

  private def load(inputFile: File, maxChunks: Option[Int]): Seq[NetflowPacket] = {
    val in = new FileInputStream(inputFile)
    val packets = mutable.Buffer[NetflowPacket]()
    val headerBytes = new Array[Byte](headerLength)
    while (maxChunks.forall(packets.length < _) && in.read(headerBytes) == headerLength) {
      val header = new NetflowHeader(headerBytes.clone())
      val records = BinaryChunks(in, flowSize, header.packetLength)
        .map(new NetflowRecord(_))

      packets += new NetflowPacket(header, records)
    }
    packets
  }

  def load(inputFile: File): Seq[NetflowPacket] = load(inputFile, None)

}
