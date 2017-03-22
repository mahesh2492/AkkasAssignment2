import akka.actor.{Props, ActorRef, Actor}
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global

class PurchaseRequestHandler extends Actor{

  val log = Logging(context.system,this)
  val validate = context.actorOf(Props[Validate])

  override def receive = {

    case (obj:Customer,num) => validate ! (obj , num)

  }

}


object PurchaseRequestHandler {

  def prop:Props = Props(classOf[PurchaseRequestHandler])
}