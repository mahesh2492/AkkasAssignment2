import akka.actor.{Props, ActorSystem}
import akka.testkit._
import com.typesafe.config.ConfigFactory
import org.scalatest.{WordSpecLike, BeforeAndAfterAll, MustMatchers}


object SamsungSpec {

  val testSystem = {
    val config = ConfigFactory.parseString(
      """
      |akka.loggers = [akka.testkit.TestEventListener]
    """.stripMargin
  )
    ActorSystem("test-system", config)
   }
  }
import SamsungSpec._


class SamsungSpec extends TestKit(testSystem) with WordSpecLike
  with BeforeAndAfterAll with MustMatchers with ImplicitSender {


  override protected def afterAll(): Unit = {
    system.terminate()
  }



  "A Validate " must {

    "respond with Error message in case customer request to buy more than 1 item" in {
      val dispatcherId = CallingThreadDispatcher.Id
      val props = Validate.prop.withDispatcher(dispatcherId)

      val ref = system.actorOf(props)
      //val ref = TestActorRef[Validate]
      EventFilter.info(message = "You can't buy more than 1 phone", occurrences = 1).intercept {
        ref ! ((Customer("", "", 1, 1), 2))
      }
    }

    "respond with success message in case if stock is not empty" in {
      val dispatcherId = CallingThreadDispatcher.Id
      val props = Validate.prop.withDispatcher(dispatcherId)

      val ref = system.actorOf(props)
      //val ref = TestActorRef[Validate]
      EventFilter.info(message = "Phone is booked ", occurrences = 1).intercept {
        ref ! ((Customer("mahesh", "", 1, 1), 1))
      }
    }

//    "respond with success message in case if stock is  empty" in {
//      val dispatcherId = CallingThreadDispatcher.Id
//      val props = Validate.prop.withDispatcher(dispatcherId)
//
//      val ref = system.actorOf(props)
//      ref.underlyingActor.noOfItemsAvailable = 0
//      //val ref = TestActorRef[Validate]
//      EventFilter.info(message = "Phone is booked ", occurrences = 1).intercept {
//        ref ! ((Customer("mahesh", "", 1, 1), 1))
//      }
//    }

  }

  "Samsung" must {
    "respond with error message in case of unkown request" in {
      val dispatcherId = CallingThreadDispatcher.Id
      val props = Samsung.prop.withDispatcher(dispatcherId)
      val ref = system.actorOf(props)
      EventFilter.info(message = "Uknown Request", occurrences = 1).intercept {
        ref ! "hello"
      }
    }
  }




  }

