package com.loryblu.data.auth.model

sealed class SignInResult {
    data class Success(val response: LoginResponse) : SignInResult()
    data class Error(val message: String) : SignInResult()
    data class ErrorWithField(val field: SignInFields, val message: String) : SignInResult()
    data object Loading : SignInResult()
    data object Empty : SignInResult()
}

enum class SignInFields {
    Email,
    Password,
    Unknown;

    companion object {
        fun getFieldByString(string: String) : SignInFields {
            return when (string) {
                "email" -> Email
                "password" -> Password
                else -> Unknown
            }
        }
    }
}