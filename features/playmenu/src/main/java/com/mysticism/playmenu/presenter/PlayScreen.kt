package com.mysticism.playmenu.presenter

import androidx.fragment.app.Fragment
import com.mysticism.core.nav.ScreenProvider

class PlayScreen() : ScreenProvider {
    override fun createFragment(): Fragment {
        return PlayFragment()
    }
}