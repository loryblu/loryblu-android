package com.loryblu.loryblu

import android.app.Application
import com.loryblu.core.network.di.networkModule
import com.loryblu.data.auth.di.authModule
import com.loryblu.data.logbook.remote.di.logbookDataModule
import com.loryblu.feature.auth.create_password.di.createNewPasswordModule
import com.loryblu.feature.auth.forgot_password.di.passwordRecoveryModule
import com.loryblu.feature.auth.login.di.loginModule
import com.loryblu.feature.auth.register.di.registerModule
import com.loryblu.feature.logbook.di.logbookModule
import com.odisby.feature.dashboard.di.dashboardModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                authModule,
                loginModule,
                registerModule,
                networkModule,
                passwordRecoveryModule,
                createNewPasswordModule,
                logbookModule,
                dashboardModule,
                logbookDataModule,
            )
        }
    }
}