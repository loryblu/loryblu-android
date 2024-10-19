package com.loryblu.core.network.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserSession(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        const val DATA = "Data"
        private const val TOKEN = "Token"
        private const val REFRESH_TOKEN = "RefreshToken"
        val tokenKey = stringPreferencesKey(TOKEN)
        val refreshTokenKey = stringPreferencesKey(REFRESH_TOKEN)
    }

    private var childIdCache: Int = 0
    private var childNameCache: String = ""
    private var parentNameCache: String = ""

    suspend fun saveToken(loginToken: String) {
        dataStore.edit {
            it[tokenKey] = loginToken
        }
    }

    fun clearToken() {
        runBlocking {
            dataStore.edit {
                it.remove(tokenKey)
                it.remove(refreshTokenKey)
            }
        }
    }

    fun saveChild(childId: Int, childName: String, parentName: String) {
        childIdCache = childId
        childNameCache = childName
        parentNameCache = parentName
    }

    fun getChildName(): String {
        return childNameCache
    }

    fun getParentName(): String {
        return parentNameCache
    }

    fun getToken(): String {
        var response: String
        runBlocking {
            val pref = dataStore.data.first()
            response = pref[tokenKey] ?: ""
        }
        return response
    }

    fun getChildId(): Int {
        return childIdCache
    }
}
