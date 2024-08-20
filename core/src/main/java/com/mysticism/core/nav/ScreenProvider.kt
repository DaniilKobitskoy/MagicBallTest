package com.mysticism.core.nav

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

interface ScreenProvider: Screen {
    fun createFragment(): Fragment
}