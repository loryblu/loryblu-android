package com.loryblu.core.util.validators

sealed class BirthdayInputValid {
    object Valid: BirthdayInputValid()
    data class Error(val messageId: Int): BirthdayInputValid()
    object EmptyError : BirthdayInputValid()
    object Empty: BirthdayInputValid()
}