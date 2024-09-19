package org.plux.marvelpedia

import android.app.Application
import org.plux.marvelpedia.di.KoinConfiguration

class MarvelpediaApp : Application(){
    override fun onCreate() {
        super.onCreate()

        KoinConfiguration.initKoin()
    }
}