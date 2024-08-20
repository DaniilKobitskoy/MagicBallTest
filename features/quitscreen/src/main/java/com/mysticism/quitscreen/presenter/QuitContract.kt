package com.mysticism.quitscreen.presenter

interface QuitContract {
    interface View {
        fun showError(message: String)
        fun openGallery()
        fun handleBackPressed()
    }

    interface Presenter {
        fun onBackPressed()
        fun onGalleryButtonClicked()
        fun onViewReady()
    }
}