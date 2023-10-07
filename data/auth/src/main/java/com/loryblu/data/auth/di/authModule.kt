package com.loryblu.data.auth.di

import com.loryblu.data.auth.api.NewPasswordApi
import com.loryblu.data.auth.api.NewPasswordApiImpl
import com.loryblu.data.auth.api.PasswordRecoveryApi
import com.loryblu.data.auth.api.PasswordRecoveryApiImpl
import com.loryblu.data.auth.api.RegisterApi
import com.loryblu.data.auth.api.RegisterApiImpl
import com.loryblu.data.auth.repository.RegisterRepository
import com.loryblu.data.auth.repository.RegisterRepositoryImpl
import org.koin.dsl.module

val authModule = module {
    single<RegisterRepository> {
        RegisterRepositoryImpl()
    }
    single<RegisterApi> {
        RegisterApiImpl(get())
    }
    single<PasswordRecoveryApi> {
        PasswordRecoveryApiImpl(get())
    }
    single<NewPasswordApi> {
        NewPasswordApiImpl(get())
    }
}