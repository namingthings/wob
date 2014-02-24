package wob

import javazoom.jl.player.advanced.{AdvancedPlayer, PlaybackListener}
import java.io.File

class SoundAlert(val filePath: String) extends PlaybackListener with Runnable {

  val urlAsString =
    "file:///" + new java.io.File(".").getCanonicalPath + File.separatorChar + filePath

  def play() {
    new Thread(this, "AudioPlayerThread").start()
  }

  override def run() {
    val device = javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
    val player = new AdvancedPlayer(new java.net.URL(urlAsString).openStream(), device)
    player.setPlayBackListener(this)
    player.play()
  }
}
