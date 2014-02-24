package wob

import akka.actor.ActorSystem
import scala.concurrent.{Await, Future}
import spray.client.pipelining._
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import spray.json._
import spray.json
import spray.http.HttpRequest
import spray.httpx.SprayJsonSupport._

object ApiJsonProtocol extends DefaultJsonProtocol {
  implicit object JokeJsonFormat extends RootJsonFormat[Joke] {
    def write(c: Joke) = ???

    def read(value: JsValue) = value match {
      case json: JsObject =>  {
        Joke(json.fields("type").convertTo[String],
          json.fields("value").asJsObject.fields("joke").convertTo[String])
      }
      case _ => json.deserializationError("joke expected")
    }
  }
}

object TeamCityApiApp extends App {
  println((new Object with TeamCityApi).getRandomJoke)
}

trait TeamCityApi extends Api {

  import ApiJsonProtocol._

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  implicit val system = ActorSystem()
  import system.dispatcher // execution context for futures

  override def getRandomJoke: Joke = {
    val pipeline: HttpRequest => Future[Joke] = sendReceive ~> unmarshal[Joke]
    Await.result(pipeline(Get("http://api.icndb.com/jokes/random")), timeout.duration)
  }
}

trait Api {
  def getRandomJoke: Joke
}
