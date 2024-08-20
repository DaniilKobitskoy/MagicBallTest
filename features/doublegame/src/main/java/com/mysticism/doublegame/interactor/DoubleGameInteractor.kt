package com.mysticism.doublegame.interactor

interface DoubleGameInteractor {
    fun getRandomIndex(): Int
    fun getItemImages(): List<Int>
    fun getTotalSteps(): Int
    fun getSpinDuration(): Long
    fun getResultDisplayDelay(): Long
}
