package com.loryblu.core.ui.models

sealed class GenderInput(val genderString: String) {
    data object MALE: GenderInput("MALE")
    data object FEMALE: GenderInput("FEMALE")
    data object Empty: GenderInput("")
    data object Error: GenderInput("")
}