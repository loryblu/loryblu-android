package com.loryblu.data.auth.api

import com.loryblu.core.network.extensions.toApiResponse
import com.loryblu.data.auth.HttpRoutes
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.model.NewPasswordRequest

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class NewPasswordApiImpl(
    private val client: HttpClient
) : NewPasswordApi {
    override suspend fun newPassword(newPasswordRequest: NewPasswordRequest): ApiResponse {
        return try {
            client.put(HttpRoutes.NEW_PASSWORD) {  // Altere esta linha para usar put
                setBody(newPasswordRequest)
                contentType(ContentType.Application.Json)
            }.toApiResponse()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            handleErrorResponse(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            handleErrorResponse(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            handleErrorResponse(e.response.status.description)
        } catch (e: Exception) {
            handleErrorResponse(e.message)
        }
    }

    private fun handleErrorResponse(errorMessage: String?): ApiResponse {
        println("Error: $errorMessage")
        return ApiResponse(listOf(), null, null)
    }
}