package com.loryblu.data.auth.api

import com.loryblu.data.auth.model.ApiResponse
import com.loryblu.data.auth.model.RegisterRequest

interface RegisterApi {
    suspend fun registerUser(registerRequest: RegisterRequest) : ApiResponse
}