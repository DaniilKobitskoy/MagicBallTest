package com.mysticism.playmenu.interactor

import com.github.terrakok.cicerone.Router
import com.mysticism.doublegame.presenter.DoubleGameScreens
import com.mysticism.firstgame.presenter.FirstGameScreens
import com.mysticism.settingsscreen.presenter.SettingsScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlayInteractorImpl : PlayInteractor, KoinComponent {

    private val router: Router by inject()

    override fun navigateToSettings() {
        router.navigateTo(SettingsScreen(router))
    }

    override fun navigateToFirstGame() {
        router.navigateTo(FirstGameScreens())
    }

    override fun navigateToDoubleGame() {
        router.navigateTo(DoubleGameScreens())
    }

    override fun exit() {
        router.exit()
    }
}