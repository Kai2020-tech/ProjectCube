package com.goodideas.projectcube

import android.app.Application
import timber.log.Timber

class CubeApp : Application() {
    override fun onCreate(){
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}