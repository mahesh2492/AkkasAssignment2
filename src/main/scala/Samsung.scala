import akka.actor.{Props, Actor}
import SamsungCounter.noOfItemsAvailable
import akka.event.Logging

class Samsung extends Actor{

  val log = Logging(context.system,this)

  val valid = context.actorOf(Props[Validate])
  def receive = {

        case obj:Customer =>  if(noOfItemsAvailable > 0) {
        noOfItemsAvailable -= 1
        log.info("\n\nThanks for Purchasing\n " + "Name: " + obj.name + "\n" + "Address: "+ obj.address + "\n" + "Mobile: "+obj.mobile) }
        else
        log.error("Sorry stock is empty.Try again later")

        case _ => log.info("Uknown Request")
  }

}

object Samsung {

  def prop:Props = Props[Samsung]
}
