package com.loryblu.loryblu.usecases

import com.loryblu.data.auth.UserAuthentication
import com.loryblu.data.auth.model.SignInResult

internal class IsUserLoggedImpl(
    private val userAuthentication: UserAuthentication
) : IsUserLogged {
    override suspend fun invoke(): Boolean {
        return when(userAuthentication.loginUserAndReturnToken()) {
            is SignInResult.Success -> true
            else -> false
        }
    }
}
