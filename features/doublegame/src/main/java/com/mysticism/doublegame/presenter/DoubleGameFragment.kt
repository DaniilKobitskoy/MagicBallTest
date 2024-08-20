package com.mysticism.doublegame.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.doublegame.R
import com.mysticism.doublegame.databinding.FragmentDoubleGameBinding
import com.mysticism.doublegame.interactor.DoubleGameInteractor
import com.mysticism.doublegame.interactor.DoubleGameInteractorImpl
import com.mysticism.doublegame.interactor.DoubleGamePresenter
import com.mysticism.doublegame.utils.ShakeDetector
import com.mysticism.game_commons.DoubleGameViewModel
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf

class DoubleGameFragment : Fragment() {

    private lateinit var presenter: DoubleGamePresenter
    private lateinit var binding: FragmentDoubleGameBinding
    private val viewModel: DoubleGameViewModel by activityViewModels()

    private lateinit var interactor: DoubleGameInteractor
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoubleGameBinding.inflate(inflater, container, false)
        ClickSoundPlayer.initialize(requireContext())

        interactor = DoubleGameInteractorImpl()

        presenter = getKoin().get { parametersOf(binding, interactor) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shakeDetector = ShakeDetector { presenter.onMagicBallClick() }
        shakeDetector.start(requireContext())

        binding.settings.setOnClickListener { presenter.onSettingsClick() }
        binding.restart.setOnClickListener { presenter.onRestartClick() }
        binding.menu.setOnClickListener { presenter.onMenuClick() }
        binding.resume.setOnClickListener { presenter.onResumeClick() }
        binding.tomainmenu.setOnClickListener { presenter.onToMainMenuClick() }
        binding.quit.setOnClickListener { presenter.onQuitClick() }
        binding.left.setOnClickListener { presenter.onLeftClick() }
        binding.right.setOnClickListener { presenter.onRightClick() }
      //  binding.magicball.setOnClickListener { presenter.onMagicBallClick() }

        restoreState()

        viewModel.resetGameEvent.observe(viewLifecycleOwner, {
            presenter.onRestartClick()
        })
    }

    private fun restoreState() {
        val itemImages = interactor.getItemImages()
        val savedIndex = presenter.getSavedItemIndex()
        val currentItemIndex = savedIndex ?: viewModel.currentItemIndex
        presenter.updateGameCoinImage(itemImages[currentItemIndex])
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
