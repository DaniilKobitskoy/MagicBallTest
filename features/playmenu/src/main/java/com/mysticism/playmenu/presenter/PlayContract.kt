package com.mysticism.playmenu.presenter

interface PlayContract {
    interface View {
        fun showPlayScreen()
        fun showPopupMenu()
        fun hidePopupMenu()
        fun navigateToSettings()
        fun navigateToFirstGame()
        fun navigateToDoubleGame()
        fun exit()
    }

    interface Presenter {
        fun onSettingsClicked()
        fun onMenuClicked()
        fun onResumeClicked()
        fun onRestartClicked()
        fun onBackToMainMenuClicked()
        fun onQuitClicked()
        fun onAskBallClicked()
        fun onPlayGameClicked()
        fun onBackClicked()
    }
}
