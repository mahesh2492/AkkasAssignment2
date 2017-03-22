import akka.actor.{Actor, Props}
import akka.event.Logging

class PurchaseRequestHandler extends Actor {

  val log = Logging(context.system, this)
  val validate = context.actorOf(Props[Validate])

  override def receive = {

    case (obj: Customer, num) => {
     // sender ! "sending data to validate actor for validation"
      validate !(obj, num)
    }
    case _ => log.info("unkown request")

  }

}


object PurchaseRequestHandler {

  def prop: Props = Props[PurchaseRequestHandler]
}