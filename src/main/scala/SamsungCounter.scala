import akka.actor.{Props, ActorSystem}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory



object SamsungCounter extends App {
  val config = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      |   router = balancing-pool
      |   nr-of-instances = 5
      | }
      |}
    """.stripMargin
  )

  // var noOfItemsAvailable= 100
  val system = ActorSystem("RouterSystem",config)

  val router = system.actorOf(FromConfig.props(Props[PurchaseRequestHandler]),"poolRouter")

  for(i <- 1 to 10)
    router ! (Customer("mahesh","Delhi",99999,971665049),1)

  //router ! (Customer("shivangi","UP",8888,886045160),2)

}