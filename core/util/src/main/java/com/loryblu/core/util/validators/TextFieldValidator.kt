package com.loryblu.core.util.validators

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class InputValid : Parcelable {
    object Valid : InputValid()
    object Empty : InputValid()
    data class Error(val messageId: Int) : InputValid()
}

@Parcelize
sealed class PasswordInputValid : InputValid(), Parcelable {
    object Valid : PasswordInputValid()
    object Empty : PasswordInputValid()
    data class Error(val messageId: Int) : PasswordInputValid()
    data class ErrorList(val errors: Map<Int, Boolean>) : PasswordInputValid()
}
