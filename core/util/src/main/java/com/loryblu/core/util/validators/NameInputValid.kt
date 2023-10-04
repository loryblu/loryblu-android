package com.loryblu.core.util.validators

sealed class NameInputValid {
    object Valid: NameInputValid()
    data class Error(val messageId: Int): NameInputValid()
    object Empty: NameInputValid()
}