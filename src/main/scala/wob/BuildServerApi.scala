package wob

case class BuildStep(name: String)

trait BuildServerApi {
  def getBuildSteps(projectId: String): Seq[BuildStep]
}