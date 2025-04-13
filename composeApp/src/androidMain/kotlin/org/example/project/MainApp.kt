package org.example.project

import android.app.Application
import io.ktor.client.engine.android.Android
import org.example.project.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(appModule(Android.create()))
        }
    }
}