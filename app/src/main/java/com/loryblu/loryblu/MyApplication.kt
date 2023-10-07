package com.loryblu.loryblu

import android.app.Application
import com.loryblu.data.auth.di.authModule
import com.loryblu.feature.auth.register.di.registerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(authModule, registerModule)
        }
    }
}