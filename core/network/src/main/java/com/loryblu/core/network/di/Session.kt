package com.loryblu.core.network.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class Session(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        const val DATA = "Data"
        private const val TOKEN = "Token"
        private const val REMEMBER = "RememberLogin"
        val token = stringPreferencesKey(TOKEN)
        val remember = booleanPreferencesKey(REMEMBER)
    }

    suspend fun saveToken(loginToken: String) {
        dataStore.edit {
            it[token] = loginToken
        }
    }

    fun getRememberLogin(): Boolean {
        var response: Boolean
        runBlocking {
            val pref = dataStore.data.first()
            response = pref[remember] ?: false
        }
        return response
    }

    suspend fun saveRememberLogin(rememberLogin: Boolean) {
        dataStore.edit {
            it[remember] = rememberLogin
        }
    }

}