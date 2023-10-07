package com.loryblu.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class PasswordRecoveryRequest(
    val email: String
)