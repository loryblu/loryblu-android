package com.loryblu.data.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.loryblu.core.network.di.UserSession
import com.loryblu.data.auth.api.LoginApi
import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.SignInResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserAuthentication(
    context: Context,
    private val loginApi: LoginApi,
    private val userSession: UserSession,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    companion object {
        private const val PREFS_NAME = "authentication_data"
        private const val EMAIL = "authentication_email"
        private const val PASSWORD = "authentication_password"
    }

    private var masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    private fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL, null)
    }

    private fun getPassword(): String? {
        return sharedPreferences.getString(PASSWORD, null)
    }

    fun saveUserCredentials(email: String, password: String) {
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
        editor.apply()
    }

    /**
     * Try to login user using email and password previously saved.
     * @return [SignInResult]
     */
    suspend fun loginUserAndReturnToken(): SignInResult = withContext(ioDispatcher) {
        val email = getEmail()
        val password = getPassword()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            return@withContext SignInResult.Error("Usuário não está conectado")
        }

        loginApi.loginUser(
            LoginRequest(
                email = email,
                password = password,
                remember = true
            )
        )
    }

    /**
     * Clear user data from UserAuthentication
     */
    fun clearUserCredentials() {
        editor.clear()
        editor.apply()
        userSession.clearToken()
    }
}
