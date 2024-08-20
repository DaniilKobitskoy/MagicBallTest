package com.mysticism.menuscreen.interactor

import com.mysticism.core.media.ClickSoundPlayer

class MenuPresenterImpl: MenuPresenter {

    private var view: MenuView? = null

    fun attachView(view: MenuView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    override fun onPlayClicked() {
        ClickSoundPlayer.playClickSound()
        view?.showPlayScreen()
    }

    override fun onSettingsClicked() {
        ClickSoundPlayer.playClickSound()
        view?.showSettingsScreen()
    }

    override fun onQuitClicked() {
        ClickSoundPlayer.playClickSound()
        view?.showQuitScreen()
    }
}
