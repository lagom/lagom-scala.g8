package $organization$.$name;format="camel"$.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service
import com.softwaremill.macwire._

class $name;format="Camel"$Loader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) with LagomDevModeComponents
}

abstract class $name;format="Camel"$Application(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[$name;format="Camel"$Service].to(wire[$name;format="Camel"$ServiceImpl])
  )

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = $name;format="Camel"$SerializerRegistry

  // Register the $name$ persistent entity
  persistentEntityRegistry.register(wire[$name;format="Camel"$Entity])
}
