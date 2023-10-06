package com.loryblu.data.auth.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val policiesAccepted: Boolean,
    val parentName: String,
    val childrenName: String,
    val childrenBirthDate: String,
    val childrenGender: String
)

object ChildrenGender {
    const val MALE = "MALE"
    const val FEMALE = "FEMALE"
}