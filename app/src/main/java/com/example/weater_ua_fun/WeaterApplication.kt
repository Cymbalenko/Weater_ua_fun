package com.example.weater_ua_fun

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeaterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
    companion object {
        var instance: WeaterApplication? = null
    }
}