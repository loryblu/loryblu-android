package com.loryblu.loryblu.di

import com.loryblu.loryblu.usecases.UserLoginChecker
import com.loryblu.loryblu.usecases.UserLoginCheckerImpl
import org.koin.dsl.module

val appModule = module {
    single<UserLoginChecker> {
        UserLoginCheckerImpl(get())
    }
}
