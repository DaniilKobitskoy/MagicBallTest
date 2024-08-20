package com.mysticism.game_commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameSharedViewModel : ViewModel() {
    private val _gameResetEvent = MutableLiveData<Boolean>()
    val gameResetEvent: LiveData<Boolean> get() = _gameResetEvent

    fun triggerGameReset() {
        _gameResetEvent.value = true
    }

    fun resetGameStateHandled() {
        _gameResetEvent.value = false
    }
}