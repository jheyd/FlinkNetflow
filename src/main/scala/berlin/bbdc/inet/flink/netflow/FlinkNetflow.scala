package berlin.bbdc.inet.flink.netflow

import java.io.File

import berlin.bbdc.inet.flink.netflow.DataSetExtensions._
import berlin.bbdc.inet.flink.netflow.binary.NetflowBinaryDecoder
import org.apache.flink.api.scala._
import org.apache.flink.api.scala.ExecutionEnvironment

object FlinkNetflow {

  val env = ExecutionEnvironment.getExecutionEnvironment

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
