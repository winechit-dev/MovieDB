package com.wcp.moviedb

import android.app.Application
import com.wcp.moviedb.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class MovieDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieDBApplication)
            androidFileProperties()
            modules(
                listOf(applicationModule, upComingModule, nowShowingModule, popularModule, detailModule))
        }
    }
}