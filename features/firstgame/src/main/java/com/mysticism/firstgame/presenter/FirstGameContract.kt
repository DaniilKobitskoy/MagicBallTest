package com.mysticism.firstgame.presenter

interface FirstGameContract {
    interface View {
        fun showRandomAnswer(imageResource: Int)
        fun updateRetryVisibility(visibility: Int)
        fun updatePlayScreenVisibility(visibility: Int)
        fun updatePopupMenuVisibility(visibility: Int)
        fun updateMagicBallImage(imageResource: Int)
        fun animateShake()
        fun resetGameState()

    }

    interface Presenter {
        fun onMagicBallClicked()
        fun onRetryClicked()
        fun onMenuClicked()
        fun onResumeClicked()
        fun onRestartClicked()
        fun onSettingsClicked()
        fun onQuitClicked()
    }
}
