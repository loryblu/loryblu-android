package com.loryblu.util.validators

sealed class EmailInputValid {
    object Valid: EmailInputValid()
    data class Error(val messageId: Int): EmailInputValid()
    object Empty: EmailInputValid()
}