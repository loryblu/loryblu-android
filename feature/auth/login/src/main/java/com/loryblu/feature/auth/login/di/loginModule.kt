package com.loryblu.feature.auth.login.di

import com.loryblu.feature.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(get(), get(), get())
    }
}
