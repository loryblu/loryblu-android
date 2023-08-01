package com.example.loryblu.createpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiStateCreatePassword(
    val showPassword: Boolean = false,
    val showConfirmationPassword: Boolean = false,
    val password: String = "",
    val confirmationPassword: String = "",
)
class CreatePasswordViewModel constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(UiStateCreatePassword())
    val uiState = _uiState

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

    fun togglePassword() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showPassword = it.showPassword.not())
            }
        }
    }
    fun toggleConfirmationPassword() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showConfirmationPassword = it.showConfirmationPassword.not())
            }
        }
    }
}