package com.loryblu.core.util.validators

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class BirthdayInputValid : Parcelable {
    data object Valid: BirthdayInputValid()
    data class Error(val messageId: Int): BirthdayInputValid()
    data object EmptyError : BirthdayInputValid()
    data object Empty: BirthdayInputValid()
}