package com.mysticism.menuscreen.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.terrakok.cicerone.Router
import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.menuscreen.databinding.FragmentMenuBinding
import com.mysticism.menuscreen.interactor.MenuPresenter
import com.mysticism.menuscreen.interactor.MenuPresenterImpl
import com.mysticism.menuscreen.interactor.MenuView
import com.mysticism.playmenu.presenter.PlayScreen
import com.mysticism.quitscreen.presenter.QuitScreen
import com.mysticism.settingsscreen.presenter.SettingsScreen
import org.koin.android.ext.android.inject

class MenuFragment : Fragment(), MenuView {
    private val router: Router by inject()
    private val presenter: MenuPresenter by inject()
    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ClickSoundPlayer.initialize(requireContext())
        (presenter as MenuPresenterImpl).attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        val playButton: ImageView = binding.play
        val settingsButton: ImageView = binding.settings
        val quitButton: ImageView = binding.quit

        playButton.setOnClickListener { presenter.onPlayClicked() }
        settingsButton.setOnClickListener { presenter.onSettingsClicked() }
        quitButton.setOnClickListener { presenter.onQuitClicked() }
    }

    override fun showPlayScreen() {
        router.navigateTo(PlayScreen())
    }

    override fun showSettingsScreen() {
        router.navigateTo(SettingsScreen(router))
    }

    override fun showQuitScreen() {
        router.navigateTo(QuitScreen())
    }

    override fun onDestroy() {
        super.onDestroy()
        (presenter as MenuPresenterImpl).detachView()
    }
}
