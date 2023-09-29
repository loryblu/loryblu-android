package com.example.loryblu.util

sealed class GenderButtonValid {
    object Valid: GenderButtonValid()
    data class Error(val messageId: Int): GenderButtonValid()
    object EmptyError : GenderButtonValid()
    object Empty: GenderButtonValid()
}