package com.mysticism.menuscreen.di

import com.github.terrakok.cicerone.Router
import com.mysticism.core.di.BaseModule
import com.mysticism.menuscreen.interactor.MenuPresenter
import com.mysticism.menuscreen.interactor.MenuPresenterImpl
import org.koin.core.module.Module
import org.koin.dsl.module

class MenuModule: BaseModule()  {
    override val module: Module = module {
        single { Router() }
        factory<MenuPresenter> { MenuPresenterImpl() }

    }
}