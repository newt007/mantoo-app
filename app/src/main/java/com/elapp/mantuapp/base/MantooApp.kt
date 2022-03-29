package com.elapp.mantuapp.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MantooApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
    }

}