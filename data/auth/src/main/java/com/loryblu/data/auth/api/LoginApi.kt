package com.loryblu.data.auth.api

import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.SignInResult

interface LoginApi {
    suspend fun loginUser(loginRequest: LoginRequest) : SignInResult
}