import java.net.InetAddress
import java.nio.ByteBuffer

case class NetflowRecord(bytes: Seq[Byte]){
  def toFlow: Flow = {
    val recordBytes = bytes.toArray

    val srcaddr = InetAddress.getByAddress(recordBytes.slice(0, 4)).getHostAddress
    val dstaddr = InetAddress.getByAddress(recordBytes.slice(4, 8)).getHostAddress

    val data = BinaryData(recordBytes)
    val dPkts = data.extractUnsignedInt(16)
    val dOctets = data.extractUnsignedInt(20)

    val srcport = data.extractUnsignedShort(32)
    val dstport = data.extractUnsignedShort(34)

    new Flow(srcaddr, dstaddr, dPkts, dOctets, srcport, dstport)
  }

}