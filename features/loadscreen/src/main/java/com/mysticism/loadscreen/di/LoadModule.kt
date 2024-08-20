package com.mysticism.loadscreen.di

import com.github.terrakok.cicerone.Router
import com.mysticism.core.di.BaseModule
import com.mysticism.loadscreen.interactor.AnimationPresenterInteractor
import com.mysticism.loadscreen.interactor.AnimationPresenterInteractorImpl
import com.mysticism.loadscreen.presenter.AnimationView
import com.mysticism.loadscreen.presenter.LoadFragment
import org.koin.core.module.Module
import org.koin.dsl.module


class LoadModule : BaseModule() {
    override val module: Module = module {
        factory<AnimationPresenterInteractor> { (view: AnimationView) ->
            AnimationPresenterInteractorImpl(view)
        }
        factory { (router: Router) ->
            LoadFragment(router)
        }
    }
}