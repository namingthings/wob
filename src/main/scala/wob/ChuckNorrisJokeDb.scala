package wob

import akka.util.Timeout
import java.util.concurrent.TimeUnit
import akka.actor.ActorSystem
import scala.concurrent.{Await, Future}
import spray.client.pipelining._
import spray.http.HttpRequest
import spray.httpx.SprayJsonSupport._
import ChuckNorrisDbJsonProtocol._

trait ChuckNorrisJokeDb extends JokeApi {

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  implicit val system: ActorSystem
  import system.dispatcher

  // execution context for futures
  def request: HttpRequest => Future[Joke] = sendReceive ~> unmarshal[Joke]

  override def getRandomJoke: Joke = {
    Await.result(request(Get("http://api.icndb.com/jokes/random")), timeout.duration)
  }
}
