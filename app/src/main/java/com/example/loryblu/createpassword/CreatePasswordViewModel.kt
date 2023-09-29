package com.example.loryblu.createpassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import com.example.loryblu.util.PasswordInputValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiStateCreatePassword(
    val showPassword: Boolean = false,
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordState: PasswordInputValid = PasswordInputValid.Empty,
    val passwordErrors: Map<Int, Boolean> = mapOf(
        R.string.at_least_eight_characters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.numbers to false,
        R.string.at_least_one_special_character to false
    ),
    val confirmPasswordState: PasswordInputValid = PasswordInputValid.Empty,
)

class CreatePasswordViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiStateCreatePassword())
    val uiState = _uiState

    var shouldGoToNextScreen = mutableStateOf(false)
        private set

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(password = newPassword)
            }
        }
    }

    fun updateConfirmationPassword(newConfirmationPassword: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(confirmationPassword = newConfirmationPassword)
            }
        }
    }

    fun passwordCheck() {
        val password = uiState.value.password
        val passwordErrors = _uiState.value.passwordErrors.toMutableMap()

        passwordErrors[R.string.at_least_eight_characters] = password.length > 8
        passwordErrors[R.string.at_least_one_uppercase_letter] = password.any { it.isUpperCase() }
        passwordErrors[R.string.lowercase_letters] = password.any { it.isLowerCase() }
        passwordErrors[R.string.numbers] = password.any { it.isDigit() }
        passwordErrors[R.string.at_least_one_special_character] = password.any { !it.isLetterOrDigit() }

        val passwordState = if (passwordErrors.values.contains(false)) {
            PasswordInputValid.Error(R.string.password_invalid)
        } else {
            PasswordInputValid.Valid
        }

        _uiState.update {
            it.copy(passwordErrors = passwordErrors, passwordState = passwordState)
        }
    }

    fun verifyConfirmationPassword(force: Boolean = false) {
        val confirmPassword = uiState.value.confirmationPassword

        if(!force && confirmPassword.isEmpty()) return

        val state = if(confirmPassword != _uiState.value.password) {
            PasswordInputValid.Error(R.string.passwords_must_be_identical)
        } else {
            PasswordInputValid.Valid
        }

        _uiState.update {
            it.copy(confirmPasswordState = state)
        }
    }

    fun verifyAllConditions() {
        viewModelScope.launch {
            passwordCheck()
            verifyConfirmationPassword(force = true)

            if(uiState.value.confirmPasswordState == PasswordInputValid.Valid && uiState.value.passwordState == PasswordInputValid.Valid){
                delay(1000)
                shouldGoToNextScreen.value = true
            }
        }
    }
}