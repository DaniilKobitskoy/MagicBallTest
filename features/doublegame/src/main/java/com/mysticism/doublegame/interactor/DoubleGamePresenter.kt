package com.mysticism.doublegame.interactor

interface DoubleGamePresenter {
    fun onSettingsClick()
    fun onRestartClick()
    fun onMenuClick()
    fun onResumeClick()
    fun onToMainMenuClick()
    fun onQuitClick()
    fun onLeftClick()
    fun onRightClick()
    fun onMagicBallClick()
    fun showPopupMenu()
    fun hidePopupMenu()
    fun showGameView()
    fun hideGamecoinList()
    fun hideCoinResult()
    fun hideWinOrLose()
    fun updateGameCoinImage(resId: Int)
    fun updateGamecoinListImage(resId: Int)
    fun updateCoinResultImage(resId: Int)
    fun showGamecoinList()
    fun showCoinResult()
    fun showWinState()
    fun showLoseState()
    fun setButtonsClickable(isClickable: Boolean)
    fun setMiniRetryClickListener(listener: () -> Unit)
    fun setMiniMenuClickListener(listener: () -> Unit)

    fun getSavedItemIndex(): Int?
}

