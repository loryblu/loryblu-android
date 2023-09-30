package com.loryblu.loryblu.util

sealed class EmailInputValid {
    object Valid: EmailInputValid()
    data class Error(val messageId: Int): EmailInputValid()
    object Empty: EmailInputValid()
}