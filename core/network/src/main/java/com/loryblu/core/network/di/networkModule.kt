package com.loryblu.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(ContentNegotiation){
                gson {
                    serializeNulls()

                }
            }
        }
    }
}
