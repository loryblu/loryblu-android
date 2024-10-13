package com.loryblu.loryblu.di

import com.loryblu.loryblu.usecases.IsUserLogged
import com.loryblu.loryblu.usecases.IsUserLoggedImpl
import org.koin.dsl.module

val appModule = module {
    single<IsUserLogged> {
        IsUserLoggedImpl(get())
    }
}
