package com.mysticism.firstgame.presenter

import androidx.fragment.app.Fragment
import com.mysticism.core.nav.ScreenProvider

class FirstGameScreens: ScreenProvider {
    override fun createFragment(): Fragment {
        return FirstGameFragment()
    }
}