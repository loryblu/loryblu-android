package com.example.loryblu.util

sealed class PasswordInputValid {
    object Valid: PasswordInputValid()
    data class Error(val messageId: Int): PasswordInputValid()
    object Empty: PasswordInputValid()
}