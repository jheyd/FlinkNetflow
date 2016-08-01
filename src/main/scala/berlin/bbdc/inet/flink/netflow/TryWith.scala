package berlin.bbdc.inet.flink.netflow

import java.io.Closeable

object TryWith {
    def tryWith[C <: Closeable, R](c: C)(function: C => R): R = {
      try {
        function(c)
      } finally {
        if (c != null)
          c.close()
      }
    }
}
