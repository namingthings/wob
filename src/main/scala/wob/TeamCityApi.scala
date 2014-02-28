package wob

import akka.actor.ActorSystem
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import scala.concurrent.{Await, Future}
import spray.client.pipelining._
import spray.http.HttpRequest
import TeamCityApiJsonProtocol._
import spray.httpx.SprayJsonSupport._

class TeamCityApi(host: String = "teamcity.jetbrains.com", implicit val system: ActorSystem) extends BuildServerApi {
  implicit val timeout = Timeout(5, TimeUnit.SECONDS)
  import system.dispatcher

  def request: HttpRequest => Future[BuildStep] = sendReceive ~> unmarshal[BuildStep]

  def getBuildSteps(projectId: String): Seq[BuildStep] = {

    Seq(Await.result(request(Get(s"http://$host/httpAuth/app/rest/projects")), timeout.duration))
  }
}
