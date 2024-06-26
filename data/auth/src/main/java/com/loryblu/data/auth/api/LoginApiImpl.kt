package com.loryblu.data.auth.api

import com.loryblu.core.network.HttpRoutes
import com.loryblu.data.auth.extensions.toSignInResult
import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.SignInResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

internal class LoginApiImpl(
    private val client: HttpClient
) : LoginApi {
    override suspend fun loginUser(loginRequest: LoginRequest) : SignInResult {
        return try {
            client.post(HttpRoutes.LOGIN) {
                setBody(loginRequest)
                contentType(ContentType.Application.Json)
            }.toSignInResult()
        } catch (e: ResponseException){
            handleErrorResponse(e.response.status)
        } catch (e: Exception) {
            handleErrorResponse(
                HttpStatusCode(
                    900,
                    e.message ?: "Unknown Exception"
                )
            )
        }
    }

    private fun handleErrorResponse(httpStatusCode: HttpStatusCode): SignInResult {
        println("Error: ${httpStatusCode.description}")
        return SignInResult.Error("Estamos com alguns problemas. Tente novamente mais tarde!")
    }
}
