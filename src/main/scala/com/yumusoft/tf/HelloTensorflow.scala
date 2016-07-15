package com.yumusoft.tf

import java.nio.FloatBuffer


object HelloTensorflow {
  def main(args: Array[String]): Unit = {
    import org.bytedeco.javacpp.tensorflow._

    val b = new GraphDefBuilder()

    val x = Const(Array(4.0f), b.opts().WithName("x"))
    Sqrt(x, b.opts().WithName("y"))

    val graphDef = new GraphDef()
    val s = b.ToGraphDef(graphDef)
    if (!s.ok()) {
      throw new Exception(s.error_message().getString)
    }

    val outputs = new TensorVector()

    val session = new Session(new SessionOptions())
    session.Create(graphDef)

    val runStatus = session.Run(
      new StringTensorPairVector(),
      new StringVector("y:0"),
      new StringVector(),
      outputs
    )

    if (!runStatus.ok()) {
      throw new Exception(runStatus.error_message().toString)
    }

    val ans = outputs.get(0).createBuffer().asInstanceOf[FloatBuffer]
    println(s"sqrt(4) = ${ans.get(0)}")

    session.close()
  }
}

