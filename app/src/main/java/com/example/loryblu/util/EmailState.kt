package com.example.loryblu.util

sealed class EmailInputValid {
    object Valid: EmailInputValid()
    data class Error(val messageId: Int): EmailInputValid()
    object Loading: EmailInputValid()
    object Empty: EmailInputValid()
}