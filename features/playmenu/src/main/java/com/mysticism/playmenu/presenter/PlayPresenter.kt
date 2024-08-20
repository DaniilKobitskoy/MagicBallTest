package com.mysticism.playmenu.presenter

import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.playmenu.interactor.PlayInteractor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlayPresenter(
    private val view: PlayContract.View
) : PlayContract.Presenter, KoinComponent {

    private val playInteractor: PlayInteractor by inject()



    override fun onSettingsClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.navigateToSettings()
    }

    override fun onMenuClicked() {
        ClickSoundPlayer.playClickSound()
        view.showPopupMenu()
    }

    override fun onResumeClicked() {
        ClickSoundPlayer.playClickSound()
        view.showPlayScreen()
    }

    override fun onRestartClicked() {
        ClickSoundPlayer.playClickSound()
        view.showPlayScreen()
    }

    override fun onBackToMainMenuClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.exit()
    }

    override fun onQuitClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.exit()
    }

    override fun onAskBallClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.navigateToFirstGame()
    }

    override fun onPlayGameClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.navigateToDoubleGame()
    }

    override fun onBackClicked() {
        ClickSoundPlayer.playClickSound()
        playInteractor.exit()
    }
}
