package com.mysticism.magicball.di.App

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val AppModule = module {
    single { Cicerone.create() }

    single { get<Cicerone<Router>>().router }

    single { get<Cicerone<Router>>().getNavigatorHolder() }

}