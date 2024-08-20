package com.mysticism.game_commons

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var ballImage: Int = R.drawable.ball_1
    var isRetryVisible: Boolean = false

    fun resetGame() {
        ballImage = R.drawable.ball_1
        isRetryVisible = false
    }
}
