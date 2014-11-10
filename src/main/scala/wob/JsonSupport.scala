package wob

import org.json4s.DefaultFormats
import spray.httpx.Json4sSupport

trait JsonSupport extends Json4sSupport {
  implicit def json4sFormats = DefaultFormats
}
