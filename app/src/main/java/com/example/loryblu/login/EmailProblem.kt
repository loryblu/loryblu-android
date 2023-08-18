package com.example.loryblu.login

sealed class EmailInputValid {
    object Valid: EmailInputValid()
    data class Error(val messageId: Int): EmailInputValid()
    object Empty: EmailInputValid()
}