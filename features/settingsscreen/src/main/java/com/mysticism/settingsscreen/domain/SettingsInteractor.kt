package com.mysticism.settingsscreen.domain

class SettingsInteractor(private val repository: SettingsRepository) {

    fun getInitialSettings(): Pair<Boolean, Boolean> {
        return Pair(repository.isMainMusicPlaying(), repository.isClickSoundEnabled())
    }

    fun toggleClickSound() {
        val newSetting = !repository.isClickSoundEnabled()
        repository.setClickSoundEnabled(newSetting)
    }

    fun toggleMusic() {
        val newSetting = !repository.isMainMusicPlaying()
        repository.setMainMusicPlaying(newSetting)
    }

    fun isClickSoundEnabled(): Boolean {
        return repository.isClickSoundEnabled()
    }

    fun isMainMusicPlaying(): Boolean {
        return repository.isMainMusicPlaying()
    }
}