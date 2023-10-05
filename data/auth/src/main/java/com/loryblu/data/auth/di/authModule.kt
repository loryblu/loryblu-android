package com.loryblu.data.auth.di

import com.loryblu.data.auth.repository.RegisterRepository
import com.loryblu.data.auth.repository.RegisterRepositoryImpl
import org.koin.dsl.module

val authModule = module {
    single<RegisterRepository> {
        RegisterRepositoryImpl()
    }
}