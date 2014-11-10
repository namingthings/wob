package wob

import akka.actor.{ActorSystem, Actor, Props}
import akka.io.IO
import spray.http.StatusCodes
import spray.routing.HttpService
import spray.can.Http

class WobServiceActor extends Actor with WobService {
  def actorRefFactory = context
  val system = context.system

  def receive = runRoute(route)
}

trait WobService extends HttpService with ScalateSupport with ChuckNorrisJokeDb {
  implicit def executionContext = actorRefFactory.dispatcher

  val renderFunction = { resourcePath: String =>
    val webPrefix: String = "web/"

    if (resourcePath.endsWith(".html") || resourcePath.endsWith(".js"))
      getFromResource(webPrefix  + resourcePath)
    else
      render(webPrefix + resourcePath)
  }

  val route = {
    path("") {
      redirect("web/index.mustache", StatusCodes.Found)
    } ~ pathPrefix("web" / Rest) { path =>
      renderFunction(path)
    } ~ path("joke") {
      complete {
        getRandomJoke
      }
    } ~ path("project" / Segment / "steps") { projectId =>
      complete {
        "steps"
      }
    }
  }
}

object WobApp extends App  {
  implicit val system = ActorSystem("wob")
  val service = system.actorOf(Props[WobServiceActor], "wob-service")

  IO(Http) ! Http.Bind(service, "0.0.0.0", port = 4242)
}
