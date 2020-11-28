package com.example.e_commerce_search

import android.app.Application
import com.example.e_commerce_search.di.mainModule
import com.e_commerce_search.app.di.dataModule
import com.e_commerce_search.app.utils.BuildConfig
import com.e_commerce_search.app.utils.ReleaseTree

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(dataModule, mainModule))
        }

        if (BuildConfig.DEBUG) {

            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}