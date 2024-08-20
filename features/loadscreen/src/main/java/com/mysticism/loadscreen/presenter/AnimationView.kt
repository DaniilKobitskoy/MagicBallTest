package com.mysticism.loadscreen.presenter

interface AnimationView {
    fun startAnimation()
    //fun stopAnimation()
    fun showImage(imageResId: Int)
    fun showImageWithAnimation(imageResId: Int)
}