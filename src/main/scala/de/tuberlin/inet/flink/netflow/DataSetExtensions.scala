package de.tuberlin.inet.flink.netflow

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.DataSet

object DataSetExtensions {

  implicit class DataSetWithDistinctWithCounts[T: TypeInformation](dataSet: DataSet[T]) {
    def distinctWithCounts(): DataSet[(T, Long)] = {
      implicit val typeInfo1 = TypeInformation.of(classOf[(T, Long)])
      dataSet
        .map((_, 1l))
        .groupBy(_._1)
        .reduce((left, right) => {
          val (key, count1) = left
          val (_, count2) = right
          (key, count1 + count2)
        })
    }
  }

}
