package com.example.loryblu.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val emailProblem: EmailProblem = EmailProblem.NONE,
    val password: String = "",
    val passwordProblem: PasswordProblem = PasswordProblem.NONE,
    // serve para salvar o estado para a proxima visita
    val isLoginSaved: Boolean = false,
    val enterTrigger: Boolean = false,
    val showPassword: Boolean = true
)

class LoginViewModel constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    fun updateIsSaved(newState: Boolean) {
        _uiState.update {
            it.copy(isLoginSaved = newState)
        }
    }
    fun IdEmailProblem(): Int {
        val problem =  when (_uiState.value.emailProblem) {
            EmailProblem.INVALID -> R.string.invalid_e_mail
            EmailProblem.EMPTY -> R.string.required_field
            EmailProblem.ABSENT -> R.string.absent_e_mail
            EmailProblem.NONE -> R.string.empty_string
        }
        return problem
    }

    fun toggleVisibility() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showPassword = _uiState.value.showPassword.not())
            }
        }
    }
}
