package com.loryblu.feature.auth.create_password.navigation.di

import com.loryblu.feature.auth.create_password.CreatePasswordViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createNewPasswordModule = module {
    viewModel<CreatePasswordViewModel> {
        CreatePasswordViewModel(get())
    }
}
