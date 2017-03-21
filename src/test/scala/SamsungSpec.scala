import akka.actor.ActorSystem
import akka.testkit.{TestKit, ImplicitSender}
import org.scalatest.{WordSpecLike, BeforeAndAfterAll, MustMatchers}

/**
  * Created by knoldus on 21/3/17.
  */
class SamsungSpec extends TestKit(ActorSystem("test-system")) with WordSpecLike
  with BeforeAndAfterAll with MustMatchers with ImplicitSender {


  override protected def afterAll(): Unit = {
    system.terminate()
  }

  "Samsung Actor " must {
    "Reply with the unkown request it receives without ask" in {
      val ref = system.actorOf(Samsung.prop)
      ref ! "some message"
      expectMsg("Uknown Request")
    }
  }

}
