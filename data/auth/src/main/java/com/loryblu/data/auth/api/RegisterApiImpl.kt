package com.loryblu.data.auth.api

import com.loryblu.core.network.extensions.toApiResponse
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.HttpRoutes
import com.loryblu.data.auth.model.RegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class RegisterApiImpl(
    private val client: HttpClient
) : RegisterApi {
    override suspend fun registerUser(registerRequest: RegisterRequest): ApiResponse {
        return try {
            client.post(HttpRoutes.REGISTER) {
                setBody(registerRequest)
                contentType(ContentType.Application.Json)
            }.toApiResponse()
        } catch(e: RedirectResponseException){
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            ApiResponse(listOf(), null, null)
        } catch(e: ClientRequestException){
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            ApiResponse(listOf(), null, null)
        } catch(e: ServerResponseException){
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            ApiResponse(listOf(), null, null)
        } catch(e: Exception){
            println("Error: ${e.message}")
            ApiResponse(listOf(), null, null)
        }
    }
}