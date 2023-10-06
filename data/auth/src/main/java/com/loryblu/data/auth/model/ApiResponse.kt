package com.loryblu.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val message: String?,
    val statusCode: Int?,
    val error: String?
)
