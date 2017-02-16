package $organization$.$name;format="camel"$stream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import $organization$.$name;format="camel"$stream.api.$name;format="Camel"$StreamService
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service
import com.softwaremill.macwire._

class $name;format="Camel"$StreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$StreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$StreamApplication(context) with LagomDevModeComponents
  
  override def describeServices = List(
    readDescriptor[$name;format="Camel"$StreamService]
  )
}

abstract class $name;format="Camel"$StreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the services that this server provides
  override lazy val lagomServer = LagomServer.forServices(
    bindService[$name;format="Camel"$StreamService].to(wire[$name;format="Camel"$StreamServiceImpl])
  )

  // Bind the $name;format="Camel"$Service client
  lazy val $name;format="camel"$Service = serviceClient.implement[$name;format="Camel"$Service]
}
