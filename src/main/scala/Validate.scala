import akka.actor.{ActorRef, Props, Actor}
import akka.event.Logging


class Validate extends Actor {

  val log = Logging(context.system,this)
 val samsung = context.actorOf(Props[Samsung])
  var noOfItemsAvailable= 8


  override def receive = {
    case (obj:Customer,num:Int) => if(num > 1) log.info("You can't buy more than 1 phone")

                                   else if(noOfItemsAvailable != 0)
                                   {
                                      noOfItemsAvailable -= 1
                                     log.info("Phone is booked ")
                                     samsung.forward(obj)
                                     }
                                  else
                                 log.info("Stock is empty. Try again later.")
    }


  }



object Validate{

  def prop:Props = Props(classOf[Validate])
}
