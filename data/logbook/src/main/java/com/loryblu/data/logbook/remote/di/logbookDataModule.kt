package com.loryblu.data.logbook.remote.di

import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.api.LogbookApiImpl
import org.koin.dsl.module

val logbookDataModule = module {
    single<LogbookApi> { LogbookApiImpl(get(), get()) }
}