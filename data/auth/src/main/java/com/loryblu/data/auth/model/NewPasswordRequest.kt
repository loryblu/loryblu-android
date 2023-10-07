package com.loryblu.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class NewPasswordRequest(
    val password: String,
    val recoveryToken: String
)