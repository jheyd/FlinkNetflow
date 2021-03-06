package berlin.bbdc.inet.flink.netflow

import java.io.File

import org.apache.flink.api.java.utils.ParameterTool

object Params {
  def fromArgs(args: Array[String]): Params = new Params(ParameterTool.fromArgs(args))
  def usage: String = "FlinkNetflow --inputFile <filename> --outputFile <filename> --flowCount <Int>"
}

class Params(parameterTool: ParameterTool) {
  def inputFile: File = new File(parameterTool.getRequired("inputFile"))
  def flowCount: Int = parameterTool.getInt("flowCount")
  def outputFile: String = parameterTool.getRequired("outputFile")
}
