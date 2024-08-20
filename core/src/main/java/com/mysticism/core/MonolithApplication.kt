package com.mysticism.core

import android.app.Application
import com.mysticism.core.di.BaseKoinInjector
import com.mysticism.core.media.ClickSoundPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

abstract class MonolithApplication: Application() {
    protected abstract val koinInjector: BaseKoinInjector

    override fun onCreate() {
        super.onCreate()
        initKoin()
        ClickSoundPlayer.initialize(this)

    }
    private fun initKoin() {
        stopKoin()
        startKoin {
            androidContext(koinInjector.androidContext)
            modules(koinInjector.modules)
        }
    }
}