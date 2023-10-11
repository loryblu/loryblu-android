package com.loryblu.core.util.validators

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PasswordInputValid : Parcelable {
    data object Valid: PasswordInputValid()
    data class ErrorList(val errors: Map<Int, Boolean>): PasswordInputValid()
    data class Error(val messageId: Int): PasswordInputValid()
    data object Empty: PasswordInputValid()
}