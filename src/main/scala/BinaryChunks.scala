import java.io.InputStream

import scala.collection.mutable

case class BinaryChunks(chunks: Seq[BinaryData])

object BinaryChunks {
  def apply(chunks: Seq[BinaryData]): BinaryChunks = new BinaryChunks(chunks)

  def apply(in: InputStream, chunkSize: Int, count: Int) = {
    val chunks = mutable.Buffer[BinaryData]()
    val buf = new Array[Byte](chunkSize)
    while (chunks.length < count && in.read(buf) == chunkSize)
      chunks += BinaryData(buf.clone())
    apply(chunks)
  }
}
