package com.mysticism.core.media

import android.content.Context
import android.media.MediaPlayer
import com.mysticism.core.R


object MusicPlayerManager {
    private var mediaPlayer: MediaPlayer? = null

    fun initialize(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.mainmusic)
            mediaPlayer?.isLooping = true
        }
    }

    fun start() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    fun stop() {
        mediaPlayer?.pause()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
