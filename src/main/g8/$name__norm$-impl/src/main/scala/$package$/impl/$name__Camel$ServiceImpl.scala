package $package$.impl

import $package$.api.{$name;format="Camel"$Service, GreetingMessage}

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer

/**
  * Implementation of the $name;format="Camel"$Service.
  */
class $name;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends $name;format="Camel"$Service {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the $name$ entity for the given ID.
    val ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }


  override def greetingsTopic(): Topic[GreetingMessage] =
    TopicProducer.singleStreamWithOffset {
      fromOffset =>
        persistentEntityRegistry.eventStream($name;format="Camel"$Event.Tag, fromOffset)
          .map(ev => (convertEvent(ev), ev.offset))
    }

  private def convertEvent(helloEvent: EventStreamElement[$name;format="Camel"$Event]): GreetingMessage = {
    helloEvent.event match {
      case GreetingMessageChanged(msg) => GreetingMessage(helloEvent.entityId, msg)
    }
  }
}