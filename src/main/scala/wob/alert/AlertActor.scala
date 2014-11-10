package wob.alert

import akka.actor.Actor

class AlertActor extends Actor {
  val soundFilesPath = "/var/sound/"

  def receive = {
    case sound: PlaySound => {
      println(s"playing $sound")
      new SoundAlert(soundFilesPath + sound.soundId).play()
    }
  }
}
