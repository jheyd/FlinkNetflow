package de.tuberlin.inet.flink.netflow.binary.util

import java.io.InputStream

import scala.collection.mutable

case class BinaryChunks(chunks: Seq[BinaryData]){
  def map[R](function: BinaryData => R): Seq[R] = chunks.map(function)
}

object BinaryChunks {
  def apply(in: InputStream, chunkSize: Int, count: Int): BinaryChunks = {
    val chunks = mutable.Buffer[BinaryData]()
    val buf = new Array[Byte](chunkSize)
    while (chunks.length < count && in.read(buf) == chunkSize)
      chunks += BinaryData(buf.clone())
    apply(chunks)
  }
}
