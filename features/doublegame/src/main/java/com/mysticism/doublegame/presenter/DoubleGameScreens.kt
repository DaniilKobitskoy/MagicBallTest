package com.mysticism.doublegame.presenter

import androidx.fragment.app.Fragment
import com.mysticism.core.nav.ScreenProvider

class DoubleGameScreens(): ScreenProvider {
    override fun createFragment(): Fragment {
        return DoubleGameFragment()
    }
}
