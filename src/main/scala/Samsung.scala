import akka.actor.{Actor, Props}
import akka.event.Logging

class Samsung extends Actor {

  val log = Logging(context.system, this)

  val valid = context.actorOf(Props[Validate])

  def receive = {

    case obj: Customer => {
      //log.info("purchase successful")
      log.info(obj.name)
    }
    case _ => log.info("Uknown Request")

  }
}

object Samsung {

  def prop: Props = Props[Samsung]
}
