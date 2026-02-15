package com.example.etravelapp.utilities

import android.view.View
import com.airbnb.lottie.LottieAnimationView

class SplashScreenManager(
    private val overlayView: View,
    private val lottieView: LottieAnimationView,
    private val onFinished: (() -> Unit)? = null
) {

    private var finishedCalled = false

    fun start() {
        finishedCalled = false
        overlayView.visibility = View.VISIBLE
        lottieView.playAnimation()
    }

    fun stop() {
        if (finishedCalled) return
        finishedCalled = true

        lottieView.cancelAnimation()
        overlayView.visibility = View.GONE
        onFinished?.invoke()
    }
}
