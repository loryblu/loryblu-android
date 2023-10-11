package com.loryblu.feature.auth.register.model

import com.loryblu.core.ui.models.GenderInput

data class Children(
    val name: String,
    val birthday: String,
    val gender: GenderInput,
    val policiesAccepted: Boolean,
)
