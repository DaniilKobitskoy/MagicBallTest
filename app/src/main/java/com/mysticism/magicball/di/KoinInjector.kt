package com.mysticism.magicball.di

import android.app.Application
import com.mysticism.core.di.BaseKoinInjector
import com.mysticism.doublegame.di.DoubleGameModule
import com.mysticism.firstgame.di.FirstGameModule
import com.mysticism.game_commons.di.GameViewModelModule
import com.mysticism.loadscreen.di.LoadModule
import com.mysticism.magicball.di.App.AppModule
import com.mysticism.menuscreen.di.MenuModule
import com.mysticism.playmenu.di.PlayMenuModule
import com.mysticism.quitscreen.di.QuitModule
import com.mysticism.settingsscreen.di.SettingsModule
import org.koin.core.module.Module

class KoinInjector(application: Application) : BaseKoinInjector(application) {
    override val modules = listOf(
        LoadModule().module,
        MenuModule().module,
        PlayMenuModule().module,
        SettingsModule().module,
        QuitModule().module,
        FirstGameModule().module,
        DoubleGameModule().module,
        GameViewModelModule().module,
        AppModule,

    )
}