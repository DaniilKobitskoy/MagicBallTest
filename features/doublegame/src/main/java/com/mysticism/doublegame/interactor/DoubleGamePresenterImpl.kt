package com.mysticism.doublegame.interactor

import android.view.View
import androidx.core.content.ContextCompat
import com.github.terrakok.cicerone.Router
import com.mysticism.core.media.ClickSoundPlayer
import com.mysticism.doublegame.R
import com.mysticism.doublegame.databinding.FragmentDoubleGameBinding
import com.mysticism.game_commons.DoubleGameViewModel
import com.mysticism.settingsscreen.presenter.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DoubleGamePresenterImpl(
    private val binding: FragmentDoubleGameBinding,
    private val interactor: DoubleGameInteractor,
    private val router: Router,
    private val doubleGameViewModel: DoubleGameViewModel
) : DoubleGamePresenter {

    private var currentItemIndex = 0
    private var isAnimationInProgress = false
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    private var savedItemIndex: Int? = null

    override fun getSavedItemIndex(): Int? {
        return savedItemIndex
    }

    override fun onSettingsClick() {
        ClickSoundPlayer.playClickSound()
        saveCurrentState()
        router.navigateTo(SettingsScreen(router))
    }

    private fun saveCurrentState() {
        savedItemIndex = currentItemIndex
    }

    override fun onRestartClick() {
        ClickSoundPlayer.playClickSound()
        restartGame()
        doubleGameViewModel.ballImage = R.drawable.item_1
        doubleGameViewModel.isRetryVisible = false
        savedItemIndex = null
    }

    private fun restartGame() {
        doubleGameViewModel.currentItemIndex = 0
        val itemImages = interactor.getItemImages()
        updateGameCoinImage(itemImages[doubleGameViewModel.currentItemIndex])
        hideGamecoinList()
        hideCoinResult()
        hideWinOrLose()
        showGameView()
        hidePopupMenu()
    }

    override fun onMenuClick() {
        ClickSoundPlayer.playClickSound()
        showPopupMenu()
    }

    override fun onResumeClick() {
        ClickSoundPlayer.playClickSound()
        hidePopupMenu()
    }

    override fun onToMainMenuClick() {
        ClickSoundPlayer.playClickSound()
        router.exit()
    }

    override fun onQuitClick() {
        ClickSoundPlayer.playClickSound()
        router.exit()
    }

    override fun onLeftClick() {
        ClickSoundPlayer.playClickSound()
        changeItem(-1)
    }

    override fun onRightClick() {
        ClickSoundPlayer.playClickSound()
        changeItem(1)
    }

    override fun onMagicBallClick() {
        ClickSoundPlayer.playClickSound()
        startRouletteAnimation()
    }

    private fun changeItem(direction: Int) {
        val itemImages = interactor.getItemImages()
        currentItemIndex = (currentItemIndex + direction + itemImages.size) % itemImages.size
        updateGameCoinImage(itemImages[currentItemIndex])
    }

    private fun startRouletteAnimation() {
        binding.winorlose.visibility = View.GONE
        if (isAnimationInProgress) return

        isAnimationInProgress = true
        setButtonsClickable(false)

        val itemImages = interactor.getItemImages()
        val totalSteps = interactor.getTotalSteps()
        val spinDuration = interactor.getSpinDuration()
        val stepDuration = spinDuration / totalSteps
        val resultDisplayDelay = interactor.getResultDisplayDelay()

        showGamecoinList()
        hideCoinResult()

        scope.launch {
            val randomIndex = interactor.getRandomIndex()

            var currentStep = 0
            while (currentStep < totalSteps) {
                delay(stepDuration)
                currentItemIndex = (currentItemIndex + 1) % itemImages.size
                updateGamecoinListImage(itemImages[currentItemIndex])
                currentStep++
            }

            val resultImage = itemImages[randomIndex]
            updateGamecoinListImage(resultImage)
            updateCoinResultImage(resultImage)

            showCoinResult()
            delay(resultDisplayDelay)

            if (currentItemIndex == randomIndex) {
                showWinState()
            } else {
                showLoseState()
            }

            setMiniRetryClickListener {
                ClickSoundPlayer.playClickSound()
                hideGamecoinList()
                hideCoinResult()
                hideWinOrLose()
            }

            setMiniMenuClickListener {
                ClickSoundPlayer.playClickSound()
                router.exit()
            }

            isAnimationInProgress = false
            setButtonsClickable(true)
        }
    }

    override fun showPopupMenu() {
        binding.gameView.visibility = View.GONE
        binding.popupMenu.visibility = View.VISIBLE
    }

    override fun hidePopupMenu() {
        binding.popupMenu.visibility = View.GONE
        binding.gameView.visibility = View.VISIBLE
    }

    override fun showGameView() {
        binding.gameView.visibility = View.VISIBLE
    }

    override fun hideGamecoinList() {
        binding.gamecoinList.visibility = View.GONE
    }

    override fun hideCoinResult() {
        binding.coinResult.visibility = View.GONE
    }

    override fun hideWinOrLose() {
        binding.winorlose.visibility = View.GONE
    }

    override fun updateGameCoinImage(resId: Int) {
        binding.gamecoin.setImageResource(resId)
    }

    override fun updateGamecoinListImage(resId: Int) {
        binding.gamecoinList.setImageResource(resId)
    }

    override fun updateCoinResultImage(resId: Int) {
        binding.coinResult.setImageResource(resId)
    }

    override fun showGamecoinList() {
        binding.gamecoinList.visibility = View.VISIBLE
    }

    override fun showCoinResult() {
        binding.coinResult.visibility = View.VISIBLE
    }

    override fun showWinState() {
        binding.star.setImageResource(R.drawable.starwin)
        binding.star2.setImageResource(R.drawable.starwin)
        binding.star3.setImageResource(R.drawable.starwin)
        binding.winorlose.visibility = View.VISIBLE
        binding.imageGameOver.background = ContextCompat.getDrawable(binding.root.context, R.drawable.gameover)
    }

    override fun showLoseState() {
        binding.star.setImageResource(R.drawable.starlose)
        binding.star2.setImageResource(R.drawable.starlose)
        binding.star3.setImageResource(R.drawable.starlose)
        binding.winorlose.visibility = View.VISIBLE
        binding.imageGameOver.background = ContextCompat.getDrawable(binding.root.context, R.drawable.lose)
    }

    override fun setButtonsClickable(isClickable: Boolean) {
        binding.settings.isClickable = isClickable
        binding.menu.isClickable = isClickable
        binding.magicball.isClickable = isClickable
        binding.left.isClickable = isClickable
        binding.right.isClickable = isClickable
        binding.restart.isClickable = isClickable
        binding.tomainmenu.isClickable = isClickable
        binding.quit.isClickable = isClickable
    }

    override fun setMiniRetryClickListener(listener: () -> Unit) {
        binding.miniretry.setOnClickListener { listener() }
    }

    override fun setMiniMenuClickListener(listener: () -> Unit) {
        binding.miniMenu.setOnClickListener { listener() }
    }
}