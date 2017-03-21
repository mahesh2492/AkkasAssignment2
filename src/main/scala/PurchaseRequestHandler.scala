import akka.actor.{Props, ActorRef, Actor}
import akka.event.Logging


class PurchaseRequestHandler extends Actor{

  val log = Logging(context.system,this)
  val validate = context.actorOf(Props[Validate])

  def receive = {

    case (obj:Customer,num) => validate ! (obj , num)
  }

}


object PurchaseRequestHandler {

  def prop(ref: ActorRef):Props = Props(classOf[PurchaseRequestHandler],ref)
}