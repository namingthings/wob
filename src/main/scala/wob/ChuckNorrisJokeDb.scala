package wob

trait ChuckNorrisJokeDb extends JokeApi with JsonSupport {
  override def getRandomJoke: Joke = {
    null
  }
}
