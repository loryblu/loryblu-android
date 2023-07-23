package com.example.loryblu.register

data class PassWordProblems(
    val lessThanEight: Boolean = false,
    val hasLowercase: Boolean = false,
    val hasUppercase: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialCharacter: Boolean = false
)
