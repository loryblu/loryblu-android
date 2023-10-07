package com.loryblu.feature.auth.register.di

import com.loryblu.feature.auth.register.child.ChildRegisterViewModel
import com.loryblu.feature.auth.register.util.FinalUserApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val registerModule = module {
    viewModel<ChildRegisterViewModel>(qualifier = named("registerScope")) {
        ChildRegisterViewModel(get(), get())
    }
    single<FinalUserApi> {
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
