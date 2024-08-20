package com.mysticism.magicball.di.App

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.mysticism.core.MonolithApplication
import com.mysticism.magicball.di.KoinInjector

class MagicGameApp: MonolithApplication() {
     override val koinInjector by lazy { KoinInjector(this) }
     lateinit var cicerone: Cicerone<Router>

     override fun onCreate() {
          super.onCreate()
          cicerone = Cicerone.create()

     }
}