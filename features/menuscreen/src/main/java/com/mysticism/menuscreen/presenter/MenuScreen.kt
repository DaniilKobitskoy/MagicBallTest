package com.mysticism.menuscreen.presenter

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.mysticism.core.nav.ScreenProvider

class MenuScreen(private val router: Router) : ScreenProvider {
    override fun createFragment(): Fragment {
        return MenuFragment()
    }
}