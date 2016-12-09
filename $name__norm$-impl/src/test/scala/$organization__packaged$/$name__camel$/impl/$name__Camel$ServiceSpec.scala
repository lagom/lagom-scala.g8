package $organization$.$name;format="camel"$.impl

import java.io.File

import akka.cluster.Cluster
import akka.persistence.cassandra.testkit.CassandraLauncher
import com.lightbend.lagom.scaladsl.persistence.cassandra.testkit.TestUtil
import com.lightbend.lagom.scaladsl.server.{LagomApplicationContext, LocalServiceLocator}
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import play.api.Configuration
import play.core.server.NettyServer
import $organization$.$name;format="camel"$.api._

import scala.concurrent.Promise

class $name;format="Camel"$ServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  CassandraLauncher.start(new File("target/$name;format="Camel"$ServiceSpec"), CassandraLauncher.DefaultTestConfigResource,
    clean = true, port = 0)

  private val port = Promise[Int]()
  private val app = new $name;format="Camel"$Application(LagomApplicationContext.Test) with LocalServiceLocator {
    override def lagomServicePort = port.future
    override def additionalConfiguration: Configuration = Configuration(TestUtil.persistenceConfig(
      "$name;format="Camel"$ServiceSpec",
      CassandraLauncher.randomPort
    ))
  }
  val server = NettyServer.fromApplication(app.application)
  port.success(server.httpPort.get)
  val client = app.serviceClient.implement[$name;format="Camel"$Service]

  // Start the cluster
  val cluster = Cluster(app.actorSystem)
  cluster.join(cluster.selfAddress)

  override protected def afterAll(): Unit = {
    server.stop()
    CassandraLauncher.stop()
  }

  "$name$ service" should {

    "say hello" in {
      client.hello("Alice").invoke().map { answer =>
        answer should ===("Hello, Alice!")
      }
    }

    "allow responding with a custom message" in {
      for {
        _ <- client.useGreeting("Bob").invoke(GreetingMessage("Hi"))
        answer <- client.hello("Bob").invoke()
      } yield {
        answer should ===("Hi, Bob!")
      }
    }
  }
}
