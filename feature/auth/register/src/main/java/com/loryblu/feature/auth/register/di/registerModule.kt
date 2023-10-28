package com.loryblu.feature.auth.register.di

import com.loryblu.feature.auth.register.RegisterViewModel
import com.loryblu.feature.auth.register.model.FinalUserApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val registerModule = module {
    viewModel(qualifier = named("registerScope")) {
        RegisterViewModel(get(), get())
    }
    single {
        FinalUserApi(
            email = "",
            password = "",
            parentName = "",
            childrenName = "",
            childrenGender = "",
            childrenBirthDate = "",
            policiesAccepted = false
        )
    }
}
