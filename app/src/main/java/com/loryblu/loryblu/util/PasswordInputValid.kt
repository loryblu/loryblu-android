package com.loryblu.loryblu.util

sealed class PasswordInputValid {
    object Valid: PasswordInputValid()
    data class Error(val messageId: Int): PasswordInputValid()
    object EmptyError : PasswordInputValid()
    object Empty: PasswordInputValid()
}