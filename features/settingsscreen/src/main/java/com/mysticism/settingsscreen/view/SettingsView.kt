package com.mysticism.settingsscreen.view


interface SettingsView {
    fun showSettings(isMusicPlaying: Boolean, isClickSoundEnabled: Boolean)
    fun updateVolumeIconForClickSound(isEnabled: Boolean)
    fun updateVolumeIconForMusic(isPlaying: Boolean)
    fun close()
    fun restartApplication()
}