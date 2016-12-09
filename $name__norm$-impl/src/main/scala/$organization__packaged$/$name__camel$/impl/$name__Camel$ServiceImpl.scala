package $organization$.$name;format="camel"$.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import $organization$.$name;format="camel"$.api.$name;format="Camel"$Service

/**
  * Implementation of the $name;format="Camel"$Service.
  */
class $name;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends $name;format="Camel"$Service {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id, None))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }
}
