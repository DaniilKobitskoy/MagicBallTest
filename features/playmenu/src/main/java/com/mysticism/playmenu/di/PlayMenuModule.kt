package com.mysticism.playmenu.di

import com.github.terrakok.cicerone.Router
import com.mysticism.core.di.BaseModule
import com.mysticism.playmenu.interactor.PlayInteractor
import com.mysticism.playmenu.interactor.PlayInteractorImpl
import com.mysticism.playmenu.presenter.PlayContract
import com.mysticism.playmenu.presenter.PlayPresenter
import org.koin.core.module.Module
import org.koin.dsl.module

class PlayMenuModule : BaseModule()  {
    override val module: Module = module {
        single { Router() }
        single<PlayInteractor> { PlayInteractorImpl() }
        factory<PlayContract.Presenter> { (view: PlayContract.View) -> PlayPresenter(view) }
    }
}