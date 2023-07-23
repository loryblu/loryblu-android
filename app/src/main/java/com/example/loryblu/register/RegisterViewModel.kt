package com.example.loryblu.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val showPassword: Boolean = true,
    val passwordProblems: PassWordProblems = PassWordProblems()
)

class RegisterViewModel constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState

    fun togglePassword() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showPassword = it.showPassword.not())
            }
        }
    }

    fun updateName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(name = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    fun updateConfirmationPassword(newConfirmationPassword: String) {
        _uiState.update {
            it.copy(confirmationPassword = newConfirmationPassword)
        }
    }
}
