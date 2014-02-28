package wob

import spray.http._
import spray.http.StatusCodes._
import spray.routing._
import MediaTypes._
import org.fusesource.scalate.{Binding, TemplateEngine}
import org.fusesource.scalate.util.{Resource, FileResourceLoader}

trait ScalateSupport {

  import Directives._

  private val templateEngine = new TemplateEngine

  // load templates from classpath
  templateEngine.resourceLoader = new FileResourceLoader {
    override def resource(uri: String): Option[Resource] = {
      val sourceFromClasspath = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(uri))
      Some(Resource.fromSource(uri, sourceFromClasspath))
    }
  }

  def render(uri: String, attributes: Map[String, Any] = Map.empty,
             extraBindings: Traversable[Binding] = Nil,
             mediaType: MediaType = `text/html`): Route = {
    respondWithMediaType(mediaType) {
      complete {
        templateEngine.layout(uri, attributes, extraBindings)
      }
    }
  }

  def renderError(errorCode: StatusCode, attributes: Map[String, Any] = Map.empty,
                  extraBindings: Traversable[Binding] = Nil,
                  mediaType: MediaType = `text/html`): Route = {
    errorCode match {
      case NotFound => render("templates/errors/" + NotFound.value + ".ssp",
        attributes, extraBindings, mediaType)
      case _ => render("templates/errors/generic.ssp",
        attributes, extraBindings, mediaType)
    }
  }

}