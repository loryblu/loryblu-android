package com.loryblu.feature.home.di

import com.loryblu.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {(navigateToLogin: () -> Unit) ->
        HomeViewModel(get(), navigateToLogin)
    }
}
