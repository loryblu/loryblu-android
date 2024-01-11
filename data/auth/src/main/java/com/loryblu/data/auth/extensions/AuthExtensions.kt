package com.loryblu.data.auth.extensions

import com.loryblu.core.network.extensions.toApiResponse
import com.loryblu.data.auth.model.LoginResponse
import com.loryblu.data.auth.model.SignInResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode


suspend fun HttpResponse.toSignInResult() : SignInResult {
    val apiResponse = this.toApiResponse()
    return if (apiResponse.serverStatusCode == HttpStatusCode.OK) {
        val response = this.body<LoginResponse>()
        SignInResult.Success(response.data.accessToken, response.data.refreshToken)
    } else {
        SignInResult.Error(apiResponse.message)
    }
}