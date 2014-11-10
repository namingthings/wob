package wob.alert

import javazoom.jl.player.advanced.{AdvancedPlayer, PlaybackListener}

class SoundAlert(val filePath: String) extends PlaybackListener {

  val urlAsString =
    "file:///" + filePath

  def play() {
    val device = javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
    val player = new AdvancedPlayer(new java.net.URL(urlAsString).openStream(), device)
    player.setPlayBackListener(this)
    player.play()
  }

}
