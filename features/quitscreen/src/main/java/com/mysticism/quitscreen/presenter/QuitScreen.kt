package com.mysticism.quitscreen.presenter

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.mysticism.core.nav.ScreenProvider

class QuitScreen() : ScreenProvider {
    override fun createFragment(): Fragment {
        return QuitFragment()
    }
}