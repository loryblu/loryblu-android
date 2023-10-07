package com.loryblu.feature.auth.forgot_password.di

import com.loryblu.feature.auth.forgot_password.ForgotPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val passwordRecoveryModule = module {
    viewModel<ForgotPasswordViewModel> {
        ForgotPasswordViewModel(get())
    }
}
