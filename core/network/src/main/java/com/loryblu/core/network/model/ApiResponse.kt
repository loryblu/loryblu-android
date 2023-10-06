package com.loryblu.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val message: List<String>,
    val statusCode: Int?,
    val error: String?
)

@Serializable
internal data class ResponseStringTemp(
    val message: String,
    val statusCode: Int?,
    val error: String?
)