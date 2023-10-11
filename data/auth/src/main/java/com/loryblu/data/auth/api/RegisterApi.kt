package com.loryblu.data.auth.api

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.model.RegisterRequest

interface RegisterApi {
    /**
     * Send request to register user in database
     * @param RegisterRequest
     * @return ApiResponse with status code = null if success, if error then status code will be the error status code
     */
    suspend fun registerUser(registerRequest: RegisterRequest) : ApiResponse
}