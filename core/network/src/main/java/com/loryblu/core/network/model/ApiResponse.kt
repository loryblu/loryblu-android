package com.loryblu.core.network.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

sealed class ApiResponse {
    data object Success : ApiResponse()
    data object ErrorDefault : ApiResponse()
    data class ErrorWithDetail(val detail: ErrorDetail) : ApiResponse()
}