import java.net.InetAddress
import java.nio.ByteBuffer

case class NetflowRecord(bytes: Seq[Byte]){
  def toFlow: Flow = {
    val data = BinaryData(bytes)

    val srcaddr = InetAddress.getByAddress(data.extractBytes(0, 4)).getHostAddress
    val dstaddr = InetAddress.getByAddress(data.extractBytes(4, 4)).getHostAddress

    val dPkts = data.extractUnsignedInt(16)
    val dOctets = data.extractUnsignedInt(20)

    val srcport = data.extractUnsignedShort(32)
    val dstport = data.extractUnsignedShort(34)

    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

}