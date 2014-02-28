package wob

import spray.json._
import spray.json

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
