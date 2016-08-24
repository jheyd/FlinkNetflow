package berlin.bbdc.inet.flink.netflow

import berlin.bbdc.inet.flink.netflow.DataSetExtensions._
import berlin.bbdc.inet.flink.netflow.binary.NetflowBinaryDecoder
import org.apache.flink.api.scala.{ExecutionEnvironment, _}

object FlinkNetflow {

  val env = ExecutionEnvironment.getExecutionEnvironment

  def main(args: Array[String]) {
    val params = Params.fromArgs(args)

    val packets = NetflowBinaryDecoder.load(params.inputFile, params.flowCount)

    val result = env.fromCollection(packets)
      .flatMap(_.records)
      .map(_.toFlow)
      .map(_.dstIp)
      .distinctWithCounts()
    result.print()
  }

}
