package com.loryblu.feature.auth.register.util

import com.loryblu.core.ui.models.GenderInput

data class Children(
    val name: String,
    val birthday: String,
    val gender: GenderInput,
    val policiesAccepted: Boolean,
)
