package $package$.impl

import $package$.api
import $package$.api.$name;format="Camel"$Service
import akka.Done
import akka.NotUsed
import akka.cluster.sharding.typed.scaladsl.ClusterSharding
import akka.cluster.sharding.typed.scaladsl.EntityRef
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.EventStreamElement
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.util.Timeout
import com.lightbend.lagom.scaladsl.api.transport.BadRequest

/**
  * Implementation of the $name;format="Camel"$Service.
  */
class $name;format="Camel"$ServiceImpl(
  clusterSharding: ClusterSharding,
  persistentEntityRegistry: PersistentEntityRegistry
)(implicit ec: ExecutionContext)
  extends $name;format="Camel"$Service {

  /**
    * Looks up the entity for the given ID.
    */
  private def entityRef(id: String): EntityRef[$name;format="Camel"$Command] =
    clusterSharding.entityRefFor($name;format="Camel"$State.typeKey, id)

  implicit val timeout = Timeout(5.seconds)

  override def doSomething(id: String): ServiceCall[NotUsed, String] = ???
}
