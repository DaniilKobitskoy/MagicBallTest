package com.mysticism.firstgame.di

import com.github.terrakok.cicerone.Router
import com.mysticism.core.di.BaseModule
import com.mysticism.firstgame.interactor.FirstGameInteractor
import com.mysticism.firstgame.presenter.FirstGameContract
import com.mysticism.firstgame.presenter.FirstGamePresenter
import org.koin.core.module.Module
import org.koin.dsl.module

class FirstGameModule : BaseModule() {
    override val module: Module = module {
        factory { (view: FirstGameContract.View) ->
            FirstGamePresenter(view, get<Router>(), get<FirstGameInteractor>())
        }

        factory { FirstGameInteractor() }

    }
}
