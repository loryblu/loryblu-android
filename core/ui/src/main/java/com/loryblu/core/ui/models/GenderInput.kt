package com.loryblu.core.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class GenderInput(val genderString: String) : Parcelable {
    data object MALE: GenderInput("MALE")
    data object FEMALE: GenderInput("FEMALE")
    data object Empty: GenderInput("")
    data object Error: GenderInput("")
}