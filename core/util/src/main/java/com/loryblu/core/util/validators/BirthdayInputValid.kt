package com.loryblu.core.util.validators

sealed class BirthdayInputValid {
    data object Valid : BirthdayInputValid()
    data class Error(val messageId: Int) : BirthdayInputValid()
    data object EmptyError : BirthdayInputValid()
    data object Empty : BirthdayInputValid()
}