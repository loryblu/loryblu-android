package com.loryblu.data.auth

import android.content.Context
import android.content.SharedPreferences
import com.loryblu.core.network.di.UserSession
import com.loryblu.core.util.createSafeEncryptedSharedPreferences
import com.loryblu.data.auth.api.LoginApi
import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.LoginResponse
import com.loryblu.data.auth.model.SignInResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Regarding the user authentication.
 */
class UserAuthentication(
    context: Context,
    private val loginApi: LoginApi,
    private val userSession: UserSession,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    // Temporary saving user email and password while refreshToken in not working.
    companion object {
        private const val PREFS_NAME = "authentication_data"
        private const val EMAIL = "authentication_email"
        private const val PASSWORD = "authentication_password"
    }

    private val sharedPreferences = createSafeEncryptedSharedPreferences(context, PREFS_NAME)

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    /**
     * Login user with email and password.
     * It will save user data to [UserSession] to be used later
     */
    fun loginWithEmailAndPassword(loginRequest: LoginRequest): Flow<SignInResult> = flow {
        emit(SignInResult.Loading)
        try {
            when (val loginResult = loginApi.loginUser(loginRequest)) {
                is SignInResult.Success -> {
                    saveUserData(loginResult.response)
                    if (loginRequest.remember) rememberUserCredentials(loginRequest)
                    emit(loginResult)
                }

                else -> emit(loginResult)
            }
        } catch (e: Exception) {
            emit(
                SignInResult.Error(
                    e.localizedMessage ?: "Unknown error"
                )
            )
        }
    }.flowOn(ioDispatcher)

    /**
     * Try to login user using email and password previously saved.
     * @return [SignInResult]
     */
    suspend fun loginWithSavedCredentials(): SignInResult = withContext(ioDispatcher) {
        val email = getEmail()
        val password = getPassword()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            return@withContext SignInResult.Error("Usuário não está conectado")
        }

        val loginRequest = LoginRequest(
            email = email,
            password = password,
            remember = true
        )

        when (val loginResult = loginApi.loginUser(loginRequest)) {
            is SignInResult.Success -> {
                saveUserData(loginResult.response)
                loginResult
            }

            else -> {
                loginResult
            }
        }
    }

    private suspend fun saveUserData(loginResponse: LoginResponse) {
        userSession.saveToken(loginResponse.data.accessToken)
        userSession.saveChild(
            loginResponse.data.user.childrens[0].id,
            loginResponse.data.user.childrens[0].fullname
        )
    }

    private fun rememberUserCredentials(
        loginRequest: LoginRequest
    ) {
        saveUserCredentials(loginRequest.email, loginRequest.password)
    }

    private fun saveUserCredentials(email: String, password: String) {
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
        editor.apply()
    }

    private fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL, null)
    }

    private fun getPassword(): String? {
        return sharedPreferences.getString(PASSWORD, null)
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
