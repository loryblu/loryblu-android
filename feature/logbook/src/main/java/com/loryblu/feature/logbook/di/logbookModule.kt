package com.loryblu.feature.logbook.di

import com.loryblu.feature.logbook.ui.task.LogbookTaskViewModel
import com.loryblu.feature.logbook.model.Category
import com.loryblu.feature.logbook.model.LogbookTaskModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logbookModule = module {
    viewModel { LogbookTaskViewModel(get(), get(), get()) }

    single<LogbookTaskModel> { LogbookTaskModel(Category.ROUTINE, "", "", listOf()) }
}
