package de.tuberlin.inet.flink.netflow

import java.io.File

import de.tuberlin.inet.flink.netflow.binary.{NetflowBinaryDecoder, NetflowHeader, NetflowPacket, NetflowRecord}
import de.tuberlin.inet.flink.netflow.flow.Flow
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.ExecutionEnvironment
import de.tuberlin.inet.flink.netflow.DataSetExtensions._

object FlinkNetflow {

  val env = ExecutionEnvironment.getExecutionEnvironment

  implicit val typeInfo = TypeInformation.of(classOf[Array[Byte]])
  implicit val typeInfo2 = TypeInformation.of(classOf[Flow])
  implicit val typeInfo3 = TypeInformation.of(classOf[List[Byte]])
  implicit val typeInfo4 = TypeInformation.of(classOf[NetflowPacket])
  implicit val typeInfo5 = TypeInformation.of(classOf[NetflowHeader])
  implicit val typeInfo6 = TypeInformation.of(classOf[NetflowRecord])
  implicit val typeInfo7 = TypeInformation.of(classOf[Int])
  implicit val typeInfo8 = TypeInformation.of(classOf[(Flow, Int)])
  implicit val typeInfo9 = TypeInformation.of(classOf[String])
  implicit val typeInfo10 = TypeInformation.of(classOf[(String, Int)])

  def main(args: Array[String]) {
    val inputFile = new File(args(0))
    val numberOfFlowsToRead = args(1).toInt

    val packets = NetflowBinaryDecoder.load(inputFile, numberOfFlowsToRead)
    val result = env.fromCollection(packets)
      .flatMap(_.records)
      .map(_.toFlow)
      .map(_.dstIp)
      .distinctWithCounts()
    result.print()
  }

}
