package com.mysticism.quitscreen.presenter

class QuitPresenter(private val view: QuitContract.View) : QuitContract.Presenter {

    override fun onViewReady() {
        // Инициализация при создании View
    }

    override fun onBackPressed() {
        // Логика для обработки кнопки назад
        view.handleBackPressed()
    }

    override fun onGalleryButtonClicked() {
        view.openGallery()
    }
}