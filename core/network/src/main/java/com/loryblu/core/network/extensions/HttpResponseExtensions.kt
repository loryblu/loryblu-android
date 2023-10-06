package com.loryblu.core.network.extensions

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.network.model.ResponseStringTemp
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend fun HttpResponse.toApiResponse() : ApiResponse {
    return try {
        this.body<ApiResponse>()
    } catch (e: io.ktor.serialization.JsonConvertException) {
        val temp = this.body<ResponseStringTemp>()
        val messageToList = listOf(temp.message)
        ApiResponse(
            message = messageToList,
            statusCode = temp.statusCode,
            error = temp.error
        )
    }
}