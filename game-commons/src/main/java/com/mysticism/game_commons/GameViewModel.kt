package com.mysticism.game_commons

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // Пример состояния игры
    var ballImage: Int = R.drawable.ball_1
    var isRetryVisible: Boolean = false
}