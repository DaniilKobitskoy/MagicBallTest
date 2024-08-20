package com.mysticism.core.media

import android.content.Context
import android.media.MediaPlayer
import com.mysticism.core.R

object ClickSoundPlayer {
    private var clickPlayer: MediaPlayer? = null
    private var isClickSoundEnabled = true

    fun initialize(context: Context) {
        if (clickPlayer == null) {
            clickPlayer = MediaPlayer.create(context, R.raw.clickmusic)
        }
    }

    fun playClickSound() {
        if (isClickSoundEnabled) {
            clickPlayer?.let { player ->
                if (!player.isPlaying) {
                    player.seekTo(0)
                    player.start()
                }
            }
        }
    }

    fun enableClickSound(enable: Boolean) {
        isClickSoundEnabled = enable
    }

    fun release() {
        clickPlayer?.release()
        clickPlayer = null
    }
}
