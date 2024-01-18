package com.loryblu.feature.logbook.di

import com.loryblu.feature.logbook.LogbookViewModel
import com.loryblu.feature.logbook.model.Category
import com.loryblu.feature.logbook.model.LogbookTaskModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logbookModule = module {
    viewModel { LogbookViewModel(get(), get(), get()) }

    single<LogbookTaskModel> { LogbookTaskModel(Category.ROUTINE, "", "", listOf()) }
}
