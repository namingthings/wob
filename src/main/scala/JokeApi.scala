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

object ChuckNorrisDbJsonProtocol extends DefaultJsonProtocol {
  implicit object JokeJsonFormat extends RootJsonFormat[Joke] {
    def write(joke: Joke) = {
      JsObject(("text", JsString(joke.text)))
    }

    def read(value: JsValue) = value match {
      case json: JsObject =>  {
        Joke(json.fields("type").convertTo[String],
          json.fields("value").asJsObject.fields("joke").convertTo[String])
      }
      case _ => json.deserializationError("joke expected")
    }
  }
}

trait ChuckNorrisJokeDb extends JokeApi {

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  implicit val system: ActorSystem

  import system.dispatcher // execution context for futures
  import ChuckNorrisDbJsonProtocol.JokeJsonFormat

  def request: HttpRequest => Future[Joke] = sendReceive ~> unmarshal[Joke]

  override def getRandomJoke: Joke = {
    Await.result(request(Get("http://api.icndb.com/jokes/random")), timeout.duration)
  }
}

trait JokeApi {
  def getRandomJoke: Joke
}
