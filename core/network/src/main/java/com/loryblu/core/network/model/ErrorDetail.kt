package com.loryblu.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDetail(
    val details: List<Details>
)

@Serializable
data class Details(
    val property: String?,
    val message: String
)