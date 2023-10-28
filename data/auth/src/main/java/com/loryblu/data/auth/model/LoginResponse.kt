package com.loryblu.data.auth.model

data class LoginResponse(
    val token: Token,
    val message: String
)

data class Token(
    val accessToken: String
)