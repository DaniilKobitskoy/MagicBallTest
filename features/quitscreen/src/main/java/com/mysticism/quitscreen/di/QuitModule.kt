package com.mysticism.quitscreen.di

import com.mysticism.core.di.BaseModule
import com.mysticism.quitscreen.presenter.QuitContract
import com.mysticism.quitscreen.presenter.QuitFragment
import com.mysticism.quitscreen.presenter.QuitPresenter
import org.koin.dsl.module

class QuitModule : BaseModule() {
    override val module = module {
        factory<QuitContract.View> { get<QuitFragment>() }

        factory { QuitPresenter(get()) }

        factory { QuitFragment() }
    }
}