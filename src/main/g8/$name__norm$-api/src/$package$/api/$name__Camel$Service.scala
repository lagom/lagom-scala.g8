package $package$.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object $name;format="Camel"$Service  {
  val TOPIC_NAME = $name
}

/**
  * The $name$ service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the $name;format="Camel"$Service.
  */
trait $name;format="Camel"$Service extends Service {

  /**
    * Example: curl http://localhost:9000/
    */
  def empty: ServiceCall[NotUsed, String] = ServiceCall { _ => Future.successful("pong") }

  /**
    * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
    * "Hi"}' http://localhost:9000/api/hello/Alice
    */
  def doSomething(id: String): ServiceCall[Any, Done]


  /**
    * This gets published to Kafka.
    */
//  def greetingsTopic(): Topic[GreetingMessageChanged]

  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("$name;format="norm"$")
      .withCalls(
        pathCall("/", empty _),
        pathCall("/api/doSomething/:id", doSomething _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}
