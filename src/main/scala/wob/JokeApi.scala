package wob

case class Joke(id: String, joke: String)

trait JokeApi {
  def getRandomJoke: Joke
}
