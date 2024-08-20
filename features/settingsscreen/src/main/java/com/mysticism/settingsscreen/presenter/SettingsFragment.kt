package com.mysticism.settingsscreen.presenter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.github.terrakok.cicerone.Router
import com.mysticism.game_commons.DoubleGameViewModel
import com.mysticism.game_commons.GameSharedViewModel
import com.mysticism.game_commons.GameViewModel
import com.mysticism.settingsscreen.R
import com.mysticism.settingsscreen.databinding.FragmentSettingsBinding
import com.mysticism.settingsscreen.view.SettingsView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingsFragment : Fragment(), SettingsView {

    private val presenter: SettingsPresenter by inject { parametersOf(gameViewModel, gameViewModel2) }
    private lateinit var binding: FragmentSettingsBinding
    private val router: Router by inject()
    private val gameViewModel: GameViewModel by activityViewModels()
    private val gameViewModel2: DoubleGameViewModel by activityViewModels()
    private val sharedViewModel: GameSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        val back: ImageView = binding.back
        val restart: ImageView = binding.restart
        val volume1: ImageView = binding.volume1
        val volume2: ImageView = binding.volume2

        back.setOnClickListener { router.exit() }
        restart.setOnClickListener {
            presenter.onRestartClicked()
            sharedViewModel.triggerGameReset()
            router.exit()
        }
        volume1.setOnClickListener { presenter.onVolume1Clicked() }
        volume2.setOnClickListener { presenter.onVolume2Clicked() }
    }

    override fun showSettings(isMusicPlaying: Boolean, isClickSoundEnabled: Boolean) {
        updateVolumeIconForClickSound(isClickSoundEnabled)
        updateVolumeIconForMusic(isMusicPlaying)
    }

    override fun updateVolumeIconForClickSound(isEnabled: Boolean) {
        binding.volume1.setImageResource(if (isEnabled) R.drawable.volon else R.drawable.voloff)
    }

    override fun updateVolumeIconForMusic(isPlaying: Boolean) {
        binding.volume2.setImageResource(if (isPlaying) R.drawable.volon else R.drawable.voloff)
    }

    override fun close() {
        (activity as? Router)?.exit()
    }

    override fun restartApplication() {
        activity?.finishAffinity()
        val intent =
            activity?.packageManager?.getLaunchIntentForPackage(activity?.packageName ?: "")
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent!!)
        System.exit(0)
    }
}