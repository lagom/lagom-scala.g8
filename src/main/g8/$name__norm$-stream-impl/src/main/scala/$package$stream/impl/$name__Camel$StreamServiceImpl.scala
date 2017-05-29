package $package$stream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import $package$stream.api.$name;format="Camel"$StreamService
import $package$.api.$name;format="Camel"$Service

import scala.concurrent.Future

/**
  * Implementation of the $name;format="Camel"$StreamService.
  */
class $name;format="Camel"$StreamServiceImpl($name;format="camel"$Service: $name;format="Camel"$Service) extends $name;format="Camel"$StreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)($name;format="camel"$Service.hello(_).invoke()))
  }
}
