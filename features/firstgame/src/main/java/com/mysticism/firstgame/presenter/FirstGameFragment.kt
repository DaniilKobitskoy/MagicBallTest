package com.mysticism.firstgame.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mysticism.firstgame.databinding.FragmentFirstGameBinding
import com.mysticism.firstgame.util.ShakeDetector
import com.mysticism.game_commons.GameSharedViewModel
import com.mysticism.game_commons.GameViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FirstGameFragment : Fragment(), FirstGameContract.View {

    private lateinit var binding: FragmentFirstGameBinding
    private val presenter: FirstGamePresenter by inject { parametersOf(this) }
    private val viewModel: GameViewModel by activityViewModels()
    private val sharedViewModel: GameSharedViewModel by activityViewModels()
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shakeDetector = ShakeDetector { presenter.onMagicBallClicked() }
        shakeDetector.start(requireContext())

        binding.retry.setOnClickListener { presenter.onRetryClicked() }
        binding.menu.setOnClickListener { presenter.onMenuClicked() }
        binding.resume.setOnClickListener { presenter.onResumeClicked() }
        binding.restart.setOnClickListener { presenter.onRestartClicked() }
        binding.settings.setOnClickListener { presenter.onSettingsClicked() }
        binding.quit.setOnClickListener { presenter.onQuitClicked() }
        binding.tomainmenu.setOnClickListener { presenter.onQuitClicked() }

        updateUIFromViewModel()

        sharedViewModel.gameResetEvent.observe(viewLifecycleOwner, Observer { resetGame ->
            if (resetGame) {
                resetGameState()
                sharedViewModel.resetGameStateHandled()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        shakeDetector.stop(requireContext())
    }

    override fun resetGameState() {
        viewModel.resetGame()
        updateUIFromViewModel()
        updatePlayScreenVisibility(View.VISIBLE)
        updatePopupMenuVisibility(View.GONE)
    }

    private fun updateUIFromViewModel() {
        binding.magicball.setImageResource(viewModel.ballImage)
        binding.retry.visibility = if (viewModel.isRetryVisible) View.VISIBLE else View.GONE
    }

    override fun showRandomAnswer(imageResource: Int) {
        viewModel.ballImage = imageResource
        binding.magicball.setImageResource(imageResource)
    }

    override fun updateRetryVisibility(visibility: Int) {
        viewModel.isRetryVisible = visibility == View.VISIBLE
        binding.retry.visibility = visibility
    }

    override fun updatePlayScreenVisibility(visibility: Int) {
        binding.mainGame.visibility = visibility
    }

    override fun updatePopupMenuVisibility(visibility: Int) {
        binding.popupMenu.visibility = visibility
    }

    override fun updateMagicBallImage(imageResource: Int) {
        viewModel.ballImage = imageResource
        binding.magicball.setImageResource(imageResource)
    }

    override fun animateShake() {
        binding.magicball.animate()
            .translationX(50f)
            .setDuration(150)
            .withEndAction {
                binding.magicball.animate()
                    .translationX(-50f)
                    .setDuration(150)
                    .withEndAction {
                        binding.magicball.animate()
                            .translationX(30f)
                            .setDuration(100)
                            .withEndAction {
                                binding.magicball.animate()
                                    .translationX(-30f)
                                    .setDuration(100)
                                    .withEndAction {
                                        binding.magicball.animate()
                                            .translationX(0f)
                                            .setDuration(150)
                                            .start()
                                    }
                                    .start()
                            }
                            .start()
                    }
                    .start()
            }
            .start()
    }
}