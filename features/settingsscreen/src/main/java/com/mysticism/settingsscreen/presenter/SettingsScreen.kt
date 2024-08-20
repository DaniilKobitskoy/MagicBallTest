package com.mysticism.settingsscreen.presenter

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.mysticism.core.nav.ScreenProvider

class SettingsScreen(private val router: Router) : ScreenProvider {
    override fun createFragment(): Fragment {
        return SettingsFragment()
    }
}