package com.example.loryblu.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val emailProblem: EmailProblem = EmailProblem.NONE,
    val password: String = "",
    val passwordProblem: PasswordProblem = PasswordProblem.NONE,
    // serve para salvar o estado para a proxima visita
    val isLoginSaved: Boolean = false,
    val enterTrigger: Boolean = false,
    var showPassword: Boolean = true
)

class LoginViewModel constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


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
    fun printEmailProblem(): String {
        val problem =  when (_uiState.value.emailProblem) {
            EmailProblem.INVALID -> "*Invalid E-mail"
            EmailProblem.EMPTY -> "*Required field"
            EmailProblem.ABSENT -> "*Absent E-mail"
            EmailProblem.NONE -> ""
        }
        return problem
    }
}