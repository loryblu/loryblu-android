package com.loryblu.data.auth.di

import com.loryblu.data.auth.UserAuthentication
import com.loryblu.data.auth.api.LoginApi
import com.loryblu.data.auth.api.LoginApiImpl
import com.loryblu.data.auth.api.NewPasswordApi
import com.loryblu.data.auth.api.NewPasswordApiImpl
import com.loryblu.data.auth.api.PasswordRecoveryApi
import com.loryblu.data.auth.api.PasswordRecoveryApiImpl
import com.loryblu.data.auth.api.RegisterApi
import com.loryblu.data.auth.api.RegisterApiImpl
import org.koin.dsl.module

val authModule = module {
    single<RegisterApi> {
        RegisterApiImpl(get())
    }

    single<PasswordRecoveryApi> {
        PasswordRecoveryApiImpl(get())
    }

    single<NewPasswordApi> {
        NewPasswordApiImpl(get())
    }

    single<LoginApi> {
        LoginApiImpl(get())
    }

    single {
        UserAuthentication(get(), get(), get())
    }

}