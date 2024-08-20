package com.mysticism.loadscreen.presenter

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.mysticism.core.nav.ScreenProvider

class LoadScreen(private val router: Router) : ScreenProvider {
    override fun createFragment(): Fragment {
        return LoadFragment(router)
    }
}