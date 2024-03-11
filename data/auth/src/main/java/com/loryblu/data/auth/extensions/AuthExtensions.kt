package com.loryblu.data.auth.extensions

import com.loryblu.core.network.extensions.toApiResponseWithDetail
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.model.SignInResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse


suspend fun HttpResponse.toSignInResult() : SignInResult {
    return when (val apiResponse = this.toApiResponseWithDetail()) {
        is ApiResponse.Success -> {
            SignInResult.Success(this.body())
        }
        is ApiResponse.ErrorWithDetail -> {
//            apiResponse.detail.details[0].run {
//                if(property == null) {
//                    return SignInResult.Error(this.message)
//                }
//                return SignInResult.ErrorWithField(field = SignInFields.getFieldByString(property!!), message = this.message)
//            }
            return SignInResult.Error("Email e/ou senha inválidos")
        }
        else -> {
            SignInResult.Error("Unknown Error")
        }
    }
}