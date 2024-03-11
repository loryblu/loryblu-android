package com.loryblu.core.network.extensions

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.network.model.ErrorDetail
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

//suspend fun HttpResponse.toApiResponse() : ApiResponse {
//    return try {
//        val response = this.body<ApiResponse>()
//        response.serverStatusCode = this.status
//        return response
//    } catch (e: io.ktor.serialization.JsonConvertException) {
//        val temp = this.body<ResponseStringTemp>()
//        val messageToList = listOf(temp.message)
//        ApiResponse(
//            message = messageToList,
//            serverStatusCode = this.status
//        )
//    }
//}

suspend fun HttpResponse.toApiResponseWithDetail() : ApiResponse {
    /**
     * Success -> just message string
     * Error ->
     * {
     *   details: Array<{
     *     property?: string;
     *     message: string;
     *   }>
     * }
     */

    return when {
        this.status.value in 200..299 -> {
            ApiResponse.Success
        }
        else -> {
            ApiResponse.ErrorWithDetail(detail = this.body<ErrorDetail>())
        }
    }
}

suspend fun HttpResponse.toApiResponse() : ApiResponse {
    /**
     * Success -> just message string
     * Error ->
     * {
     *   details: Array<{
     *     property?: string;
     *     message: string;
     *   }>
     * }
     */

    return when {
        this.status.value in 200..299 -> {
            ApiResponse.Success
        }
        else -> {
            ApiResponse.ErrorDefault
        }
    }
}
