package com.loryblu.feature.logbook.di

import com.loryblu.feature.logbook.LogbookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logbookModule = module {
    viewModel {(navigateToLogin: () -> Unit) ->
        LogbookViewModel(get(), navigateToLogin)
    }
}
