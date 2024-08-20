package com.mysticism.firstgame.interactor

import com.mysticism.firstgame.R
import kotlinx.coroutines.delay

class FirstGameInteractor {

    private val ballImages = listOf(
        R.drawable.ball_2, R.drawable.ball_3, R.drawable.ball_4, R.drawable.ball_5,
        R.drawable.ball_6, R.drawable.ball_7, R.drawable.ball_8, R.drawable.ball_9,
        R.drawable.ball_10, R.drawable.ball_11, R.drawable.ball_12, R.drawable.ball_13,
        R.drawable.ball_14, R.drawable.ball_15, R.drawable.ball_16, R.drawable.ball_17,
        R.drawable.ball_18, R.drawable.ball_19, R.drawable.ball_20, R.drawable.ball_21
    )

    fun getRandomBallImage(): Int {
        return ballImages.random()
    }

    suspend fun delayForAnswer() {
        delay(1000)
    }

    fun getInitialBallImage(): Int {
        return R.drawable.ball_1
    }
}