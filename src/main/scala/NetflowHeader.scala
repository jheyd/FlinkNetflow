import java.nio.ByteBuffer

case class NetflowHeader(bytes: Seq[Byte]) {
  def packetLength: Int = ByteBuffer.wrap(Array[Byte](0, 0) ++ bytes.slice(2, 4)).getInt
}