package com.mysticism.playmenu.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.playmenu.databinding.FragmentPlayBinding
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PlayFragment : Fragment(), PlayContract.View {

    private lateinit var presenter: PlayContract.Presenter
    private lateinit var binding: FragmentPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize ClickSoundPlayer here
        ClickSoundPlayer.initialize(requireContext())

        // Get Presenter with current View instance
        presenter = getKoin().get(qualifier = null, parameters = { parametersOf(this) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.settings.setOnClickListener { presenter.onSettingsClicked() }
        binding.menu.setOnClickListener { presenter.onMenuClicked() }
        binding.resume.setOnClickListener { presenter.onResumeClicked() }
        binding.restart.setOnClickListener { presenter.onRestartClicked() }
        binding.tomainmenu.setOnClickListener { presenter.onBackToMainMenuClicked() }
        binding.quit.setOnClickListener { presenter.onQuitClicked() }
        binding.askBall.setOnClickListener { presenter.onAskBallClicked() }
        binding.playGame.setOnClickListener { presenter.onPlayGameClicked() }
        binding.back.setOnClickListener { presenter.onBackClicked() }
    }

    override fun showPlayScreen() {
        binding.playScreen.visibility = View.VISIBLE
        binding.popupMenu.visibility = View.GONE
    }

    override fun showPopupMenu() {
        binding.playScreen.visibility = View.GONE
        binding.popupMenu.visibility = View.VISIBLE
    }

    override fun hidePopupMenu() {
        binding.popupMenu.visibility = View.GONE
    }

    override fun navigateToSettings() {
        presenter.onSettingsClicked()
    }

    override fun navigateToFirstGame() {
        presenter.onAskBallClicked()
    }

    override fun navigateToDoubleGame() {
       presenter.onPlayGameClicked()
    }

    override fun exit() {
       presenter.onBackClicked()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release resources if necessary
        ClickSoundPlayer.release() // Uncomment if ClickSoundPlayer.release() is necessary
    }
}
