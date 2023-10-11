package com.loryblu.core.util.validators

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class NameInputValid : Parcelable {
    data object Valid: NameInputValid()
    data class Error(val messageId: Int): NameInputValid()
    data object Empty: NameInputValid()
}
