package com.mysticism.firstgame.presenter

import android.view.View
import com.github.terrakok.cicerone.Router
import com.mysticism.firstgame.interactor.FirstGameInteractor
import com.mysticism.settingsscreen.presenter.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FirstGamePresenter(
    private val view: FirstGameContract.View,
    private val router: Router,
    private val interactor: FirstGameInteractor,
    private val coroutineContext: CoroutineContext = Dispatchers.Main
) : FirstGameContract.Presenter {

    private val scope = CoroutineScope(coroutineContext)

    override fun onMagicBallClicked() {
        view.animateShake()
        scope.launch {
            interactor.delayForAnswer()
            val randomImage = interactor.getRandomBallImage()
            view.showRandomAnswer(randomImage)
            view.updateRetryVisibility(View.VISIBLE)
        }
    }

    override fun onRetryClicked() {
        view.updateRetryVisibility(View.GONE)
        view.updateMagicBallImage(interactor.getInitialBallImage())
    }

    override fun onMenuClicked() {
        view.updatePlayScreenVisibility(View.GONE)
        view.updatePopupMenuVisibility(View.VISIBLE)
    }

    override fun onResumeClicked() {
        view.updatePopupMenuVisibility(View.GONE)
        view.updatePlayScreenVisibility(View.VISIBLE)
    }

    override fun onRestartClicked() {
        view.resetGameState()
        view.updateMagicBallImage(interactor.getInitialBallImage())
        view.updateRetryVisibility(View.GONE)
        view.updatePlayScreenVisibility(View.VISIBLE)
        view.updatePopupMenuVisibility(View.GONE)
    }

    override fun onSettingsClicked() {
        router.navigateTo(SettingsScreen(router))
    }

    override fun onQuitClicked() {
        router.exit()
    }
}
