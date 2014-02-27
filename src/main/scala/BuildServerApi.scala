package wob

import scala.concurrent.{Await, Future}
import spray.client.pipelining._
import spray.http.HttpRequest
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import akka.actor.ActorSystem
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol
import spray.json.RootJsonFormat
import spray.json.JsValue
import spray.json.JsObject
import spray.json
import spray.json.JsString
import spray.http.HttpRequest

case class BuildStep(name: String)

trait BuildServerApi {
  def getBuildSteps(projectId: String): Seq[BuildStep]
}

class TeamCityApi(host: String = "teamcity.jetbrains.com", implicit val system: ActorSystem) extends BuildServerApi {
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  import system.dispatcher

  import TeamCityApiJsonProtocol._
  //def request: HttpRequest => Future[BuildStep] = sendReceive ~> unmarshal[BuildStep]

  def getBuildSteps(projectId: String): Seq[BuildStep] = {
    //Seq(Await.result(request(Get(s"http://$host/httpAuth/app/rest/projects")), timeout.duration))
    Seq()
  }
}

object TeamCityApiJsonProtocol extends DefaultJsonProtocol {
  implicit object BuildStepJsonFormat extends RootJsonFormat[BuildStep] {
    def write(buildStep: BuildStep) = {
      JsObject(("name", JsString(buildStep.name)))
    }

    def read(value: JsValue) = value match {
      case json: JsObject =>  {
        BuildStep("")
      }
      case _ => json.deserializationError("buildstep expected")
    }
  }
}
