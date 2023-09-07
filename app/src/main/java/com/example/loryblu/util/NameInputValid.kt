package com.example.loryblu.util

sealed class NameInputValid {
    object Valid: NameInputValid()
    data class Error(val messageId: Int): NameInputValid()
    object Empty: NameInputValid()
}