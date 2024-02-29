package com.loryblu.core.network.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.loryblu.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(get<HttpClientEngine>()) {

            install(ContentNegotiation){
                gson {
                    serializeNulls()
                    setPrettyPrinting()
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
        }

    }

    single<ChuckerInterceptor> {
        ChuckerInterceptor.Builder(get())
            .collector(ChuckerCollector(
                context = get(),
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            ))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    single<HttpClientEngine> {
        OkHttp.create {
            addNetworkInterceptor(get<ChuckerInterceptor>())
        }
    }

    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { androidContext().preferencesDataStoreFile(Session.DATA) })
    }
    single {
        Session(get())
    }
}
