package $package$stream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The $name$ stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the $name;format="Camel"$Stream service.
  */
trait $name;format="Camel"$StreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("$name;format="norm"$-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

