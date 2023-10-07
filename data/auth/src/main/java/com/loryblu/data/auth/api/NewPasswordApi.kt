package com.loryblu.data.auth.api

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.model.NewPasswordRequest
import com.loryblu.data.auth.model.PasswordRecoveryRequest
import com.loryblu.data.auth.model.RegisterRequest

interface NewPasswordApi {
    suspend fun newPassword(newPasswordRequest: NewPasswordRequest) : ApiResponse
}