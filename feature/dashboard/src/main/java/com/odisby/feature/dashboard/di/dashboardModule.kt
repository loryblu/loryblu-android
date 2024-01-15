package com.odisby.feature.dashboard.di

import com.odisby.feature.dashboard.ui.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel {
        DashboardViewModel(get())
    }
}