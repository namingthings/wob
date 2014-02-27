package wob

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

object WobApp extends App with SimpleRoutingApp with ScalateSupport with ChuckNorrisJokeDb {
  implicit val system = ActorSystem("wob")
  val teamCityApi = new TeamCityApi(system = system)

  val renderFunction = { resourcePath: String =>
    val webPrefix: String = "web/"

    if (resourcePath.endsWith(".html") || resourcePath.endsWith(".js"))
      getFromResource(webPrefix  + resourcePath)
    else
      render(webPrefix + resourcePath)
  }

  startServer(interface = "localhost", port = 9000) {
    path("") {
      redirect("web/index.mustache", StatusCodes.Found)
    } ~ pathPrefix("web" / Rest) { path =>
      renderFunction(path)
    } ~ path("joke") {
      complete {
        import ChuckNorrisDbJsonProtocol._
        getRandomJoke
      }
    } ~ path("project" / Segment / "steps") { projectId =>
      complete {
        import TeamCityApiJsonProtocol.BuildStepJsonFormat
        teamCityApi.getBuildSteps(projectId)
      }
    }
  }
}
