package wob

import com.google.gson.internal.bind.DateTypeAdapter
import com.google.gson.GsonBuilder
import org.apache.commons.codec.binary.{StringUtils, Base64}
import retrofit.RequestInterceptor.RequestFacade
import retrofit.converter.GsonConverter
import retrofit.{RequestInterceptor, RestAdapter}
import retrofit.http.{Path, Headers, GET}

case class BuildResults(count: Int, build: Array[BuildResult]) {
  def builds = build.toList
}

case class BuildResult(id: String, number: Int, status: String, buildTypeId: String, startDate: String, href: String, webUrl: String)

trait TeamCityApi {
  @Headers(Array("Accept: application/json"))
  @GET("/httpAuth/app/rest/buildTypes/{id}/builds/")
  def getBuilds(@Path("id") buildType: String): BuildResults
}

class TeamCityClient(url: String, userName: String, password: String) extends TeamCityApi {
  val gson = new GsonBuilder()
    .registerTypeAdapter(classOf[java.util.Date], new DateTypeAdapter())
    .create()

  val restAdapter = new RestAdapter.Builder()
    .setRequestInterceptor(authInterceptor)
    .setConverter(new GsonConverter(gson))
    .setEndpoint(url)
    .build()

  def authInterceptor = new RequestInterceptor() {
    override def intercept(request: RequestFacade) = {
      request.addHeader("Authorization", authorizationHeaderValue(userName, password))
    }

    def authorizationHeaderValue(userName: String, password: String) = {
      val userColonPassword = base64(s"$userName:$password")
      s"Basic $userColonPassword"
    }

    def base64(input: String) = {
      Base64.encodeBase64String(StringUtils.getBytesUtf8(input))
    }
  }

  val teamCityApi: TeamCityApi = restAdapter.create(classOf[TeamCityApi])

  override def getBuilds(buildType: String): BuildResults = teamCityApi.getBuilds(buildType)
}

object TeamCityTest extends App {
  println(new TeamCityClient("https://teamcity.bis.epost-dev.de", "guest", "guest").getBuilds("bt131"))
}
