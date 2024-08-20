package com.mysticism.doublegame.interactor

import com.mysticism.doublegame.R
import kotlin.random.Random

class DoubleGameInteractorImpl : DoubleGameInteractor {
    override fun getRandomIndex(): Int {
        return Random.nextInt(getItemImages().size)
    }

    override fun getItemImages(): List<Int> {
        return listOf(
            R.drawable.item_1,
            R.drawable.item_2,
            R.drawable.item_3,
            R.drawable.item_4,
            R.drawable.item_5,
            R.drawable.item_6,
            R.drawable.item_7,
            R.drawable.item_8,
            R.drawable.item_9,
            R.drawable.item_10
        )
    }

    override fun getTotalSteps(): Int = 30
    override fun getSpinDuration(): Long = 3000L
    override fun getResultDisplayDelay(): Long = 1000L
}
