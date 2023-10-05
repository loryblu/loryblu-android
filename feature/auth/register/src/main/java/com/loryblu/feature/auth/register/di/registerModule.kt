package com.loryblu.feature.auth.register.di

import com.loryblu.feature.auth.register.child.ChildRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel<ChildRegisterViewModel> {
        ChildRegisterViewModel(get())
    }
}
