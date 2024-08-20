package com.mysticism.loadscreen.interactor


import com.mysticism.loadscreen.R
import com.mysticism.loadscreen.presenter.AnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimationPresenterInteractorImpl(
    private val view: AnimationView,
) : AnimationPresenterInteractor {

    private var job: Job? = null

    override fun onStart() {
        job = CoroutineScope(Dispatchers.Main).launch {
            startImageSequence()
        }
    }

    override fun onStop() {
        job?.cancel()
    }

    private suspend fun startImageSequence() {
        val images = listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8
        )

        for (image in images) {
            view.showImage(image)
            delay(500)
        }

        view.startAnimation()
    }
}