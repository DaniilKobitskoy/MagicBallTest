package com.mysticism.quitscreen.presenter

class QuitPresenter(private val view: QuitContract.View) : QuitContract.Presenter {

    override fun onViewReady() {
    }

    override fun onBackPressed() {
        view.handleBackPressed()
    }

    override fun onGalleryButtonClicked() {
        view.openGallery()
    }
}