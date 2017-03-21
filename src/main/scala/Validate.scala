import akka.actor.{ActorRef, Props, Actor}
import akka.event.Logging

/**
  * Created by knoldus on 21/3/17.
  */
class Validate extends Actor {

  val log = Logging(context.system,this)
 val samsung = context.actorOf(Props[Samsung])


  def receive = {
    case (obj:Customer,num:Int) => if(num > 1) log.error("You can't buy more than 1 phone\n") else samsung ! obj

  }

}

object Validate{

  def prop(ref: ActorRef):Props = Props(classOf[Validate],ref)
}
