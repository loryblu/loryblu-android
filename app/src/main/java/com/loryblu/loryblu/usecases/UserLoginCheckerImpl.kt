package com.loryblu.loryblu.usecases

import com.loryblu.data.auth.UserAuthentication
import com.loryblu.data.auth.model.SignInResult

internal class UserLoginCheckerImpl(
    private val userAuthentication: UserAuthentication
) : UserLoginChecker {
    override suspend fun invoke(): Boolean {
        return when(userAuthentication.loginWithSavedCredentials()) {
            is SignInResult.Success -> true
            else -> false
        }
    }
}
