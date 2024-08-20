package com.mysticism.magicball

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mysticism.magicball.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.core.media.MusicPlayerManager
import com.mysticism.loadscreen.presenter.LoadFragment
import com.mysticism.playmenu.presenter.PlayFragment
import com.mysticism.menuscreen.presenter.MenuFragment
import com.mysticism.settingsscreen.presenter.SettingsFragment
import com.mysticism.quitscreen.presenter.QuitFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()

    companion object {
        private const val LAST_SCREEN_TAG = "last_screen"
        private const val SCREEN_LOAD = "LoadScreen"
        private const val SCREEN_PLAY = "PlayFragment"
        private const val SCREEN_SETTINGS = "SettingsFragment"
        private const val SCREEN_QUIT = "QuitFragment"
        private const val SCREEN_MENU = "MenuFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val isMusicPlaying = sharedPreferences.getBoolean("isMusicPlaying", true)
        val isClickSoundEnabled = sharedPreferences.getBoolean("isClickSoundEnabled", true)
        MusicPlayerManager.initialize(this)
        ClickSoundPlayer.initialize(this)
        ClickSoundPlayer.enableClickSound(isClickSoundEnabled)

        if (isMusicPlaying) {
            MusicPlayerManager.start()
        }

        if (savedInstanceState == null) {
            replaceFragment(LoadFragment(router), SCREEN_LOAD)
        } else {
            val lastScreen = savedInstanceState.getString(LAST_SCREEN_TAG)
            lastScreen?.let {
                replaceFragmentByTag(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(NavigatorImpl(this, binding.fragmentContainer.id))

        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val isMusicPlaying = sharedPreferences.getBoolean("isMusicPlaying", true)
        if (isMusicPlaying) {
            MusicPlayerManager.start()
        }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()

        MusicPlayerManager.stop()
        ClickSoundPlayer.release()
    }

    override fun onStop() {
        super.onStop()

        MusicPlayerManager.stop()
        ClickSoundPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        MusicPlayerManager.release()
        ClickSoundPlayer.release()
    }

    private fun replaceFragmentByTag(tag: String) {
        val fragment = when (tag) {
            SCREEN_PLAY -> PlayFragment()
            SCREEN_SETTINGS -> SettingsFragment()
            SCREEN_QUIT -> QuitFragment.newInstance()
            SCREEN_MENU -> MenuFragment()
            SCREEN_LOAD -> LoadFragment(router)
            else -> null
        }
        fragment?.let {
            replaceFragment(it, tag)
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment, tag)
            .commit()
    }
}
