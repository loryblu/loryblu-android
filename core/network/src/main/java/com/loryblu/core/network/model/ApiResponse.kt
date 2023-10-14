package com.loryblu.core.network.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val message: List<String>,
    @Contextual var serverStatusCode: HttpStatusCode,
)

//@Serializable
//data class HttpStatusCodeServer(
//    val value: Int,
//    val description: String
//)

@Serializable
internal data class ResponseStringTemp(
    val message: String,
    val statusCode: Int?,
    val error: String?
)