package com.example.etravelapp

import android.app.Application
import com.example.etravelapp.utilities.ImageLoader
import com.example.etravelapp.utilities.SignalManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SignalManager.Companion.init(this)
        ImageLoader.init(this)

    }
}