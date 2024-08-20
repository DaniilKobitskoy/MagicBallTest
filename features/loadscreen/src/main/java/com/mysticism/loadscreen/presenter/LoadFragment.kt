package com.mysticism.loadscreen.presenter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.terrakok.cicerone.Router
import com.mysticism.loadscreen.databinding.FragmentLoadBinding
import com.mysticism.loadscreen.interactor.AnimationPresenterInteractor
import com.mysticism.menuscreen.presenter.MenuScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoadFragment(private val router: Router) : Fragment(), AnimationView {

    private lateinit var binding: FragmentLoadBinding
    private lateinit var imageView: ImageView
    private val animationDuration = 300L

    private val presenter: AnimationPresenterInteractor by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = binding.imageView
        presenter.onStart()
        startAnimationWithDelay()
    }

    override fun startAnimation() {
        presenter.onStart()
    }
    override fun showImage(imageResId: Int) {
        imageView.setImageResource(imageResId)
    }

    override fun showImageWithAnimation(imageResId: Int) {
        ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f).apply {
            duration = animationDuration
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    imageView.setImageResource(imageResId)
                    ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f).apply {
                        duration = animationDuration
                        start()
                    }
                }
            })
            start()
        }
    }

    private fun startAnimationWithDelay() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            navigateToMenuScreen()
        }
    }

    private fun navigateToMenuScreen() {

        router.navigateTo(MenuScreen(router))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}
