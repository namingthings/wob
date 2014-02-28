package wob

case class Joke(jokeType: String, text: String)

trait JokeApi {
  def getRandomJoke: Joke
}
