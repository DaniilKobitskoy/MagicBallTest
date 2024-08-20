package com.mysticism.game_commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DoubleGameViewModel : ViewModel() {
    var currentItemIndex: Int = 0
    var ballImage: Int = R.drawable.item_1
    var isRetryVisible: Boolean = false

    private val _resetGameEvent = MutableLiveData<Unit>()
    val resetGameEvent: LiveData<Unit> get() = _resetGameEvent

    fun resetGame() {
        _resetGameEvent.postValue(Unit)
    }
}