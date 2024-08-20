package com.mysticism.settingsscreen.domain

import android.content.SharedPreferences

class SettingsRepository(private val sharedPreferences: SharedPreferences) {

    fun isMainMusicPlaying(): Boolean {
        return sharedPreferences.getBoolean("isMusicPlaying", true)
    }

    fun isClickSoundEnabled(): Boolean {
        return sharedPreferences.getBoolean("isClickSoundEnabled", true)
    }

    fun setMainMusicPlaying(isPlaying: Boolean) {
        sharedPreferences.edit().putBoolean("isMusicPlaying", isPlaying).apply()
    }

    fun setClickSoundEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean("isClickSoundEnabled", isEnabled).apply()
    }
}