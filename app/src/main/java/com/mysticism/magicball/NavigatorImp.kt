package com.mysticism.magicball

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Screen
import com.mysticism.doublegame.presenter.DoubleGameScreens
import com.mysticism.firstgame.presenter.FirstGameScreens
import com.mysticism.loadscreen.presenter.LoadScreen
import com.mysticism.menuscreen.presenter.MenuScreen
import com.mysticism.playmenu.presenter.PlayScreen
import com.mysticism.quitscreen.presenter.QuitScreen
import com.mysticism.settingsscreen.presenter.SettingsScreen

class NavigatorImpl(
    private val activity: Activity,
    private val containerId: Int
) : Navigator {

    private val fragmentManager: FragmentManager = (activity as AppCompatActivity).supportFragmentManager

    override fun applyCommands(commands: Array<out Command>) {
        commands.forEach { command ->
            when (command) {
                is Forward -> navigateTo(command.screen)
                is Replace -> replaceScreen(command.screen)
                is Back -> goBack()
            }
        }
    }

    private fun navigateTo(screen: Screen) {
        val fragment = getFragmentForScreen(screen)
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceScreen(screen: Screen) {
        val fragment = getFragmentForScreen(screen)
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }

    private fun goBack() {
        fragmentManager.popBackStack()
    }

    private fun getFragmentForScreen(screen: Screen): Fragment {
        return when (screen) {
            is MenuScreen -> screen.createFragment()
            is PlayScreen -> screen.createFragment()
            is SettingsScreen -> screen.createFragment()
            is QuitScreen -> screen.createFragment()
            is LoadScreen -> screen.createFragment()
            is FirstGameScreens -> screen.createFragment()
            is DoubleGameScreens -> screen.createFragment()
            else -> throw IllegalArgumentException("Unknown screen type: $screen")
        }
    }
}
