package wob

import spray.json
import spray.json._

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
