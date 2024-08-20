package com.mysticism.settingsscreen.presenter


import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.game_commons.DoubleGameViewModel
import com.mysticism.game_commons.GameViewModel
import com.mysticism.settingsscreen.R
import com.mysticism.settingsscreen.domain.SettingsInteractor
import com.mysticism.settingsscreen.view.SettingsView

class SettingsPresenter(private val interactor: SettingsInteractor, private val gameViewModel: GameViewModel,  private val gameViewModel2: DoubleGameViewModel) {

    private var view: SettingsView? = null

    fun attachView(view: SettingsView) {
        this.view = view
        val (isMusicPlaying, isClickSoundEnabled) = interactor.getInitialSettings()
        view.showSettings(isMusicPlaying, isClickSoundEnabled)
    }

    fun onBackClicked() {
        view?.close()
        playClickSound()
    }

    fun onRestartClicked() {
        // Reset game state
        gameViewModel.ballImage = R.drawable.ball_1
        gameViewModel.isRetryVisible = false
        gameViewModel2.resetGame()
        // view?.restartApplication()
        playClickSound()
    }

    fun onVolume1Clicked() {
        interactor.toggleClickSound()
        view?.updateVolumeIconForClickSound(interactor.isClickSoundEnabled())
        playClickSound()
    }

    fun onVolume2Clicked() {
        interactor.toggleMusic()
        view?.updateVolumeIconForMusic(interactor.isMainMusicPlaying())
        playClickSound()
    }

    private fun playClickSound() {
        if (interactor.isClickSoundEnabled()) {
            ClickSoundPlayer.playClickSound()
        }
    }
}