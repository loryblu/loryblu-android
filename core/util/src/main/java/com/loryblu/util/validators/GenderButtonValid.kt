package com.loryblu.util.validators

sealed class GenderButtonValid {
    object Valid: GenderButtonValid()
    data class Error(val messageId: Int): GenderButtonValid()
    object EmptyError : GenderButtonValid()
    object Empty: GenderButtonValid()
}