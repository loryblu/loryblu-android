package com.example.loryblu.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val emailProblem: EmailInputValid = EmailInputValid.Empty,
    val password: String = "",
    val passwordProblem: PasswordProblem = PasswordProblem.EMPTY,
    // serve para salvar o estado para a proxima visita
    val isLoginSaved: Boolean = true,
    val enterTrigger: Boolean = false,
    val showPassword: Boolean = true
)

class LoginViewModel constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState

    var authenticated = mutableStateOf(false)
        private set

    fun emailState(email: String) {
        when {
            email.isEmpty() -> {
                _uiState.update {
                    it.copy(emailProblem = EmailInputValid.Error(R.string.empty_email))
                }
            }
            email.isEmailValid().not() -> {
                _uiState.update {
                    it.copy(emailProblem = EmailInputValid.Error(R.string.invalid_e_mail))
                }
            }
            else -> {
                _uiState.update {
                    it.copy(emailProblem = EmailInputValid.Success)
                }
            }
        }
    }
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

    fun toggleIsLoginSaved() {
        _uiState.update {
            it.copy(isLoginSaved = it.isLoginSaved.not())
        }
    }

    fun verifyPassword(newPassword: String) {
        when {
            newPassword.trim() == "" -> {
                _uiState.update {
                    it.copy(passwordProblem = PasswordProblem.EMPTY)
                }
            }
            // procura no banco de dados pelas informações de login da pessoa
        }
    }

    fun loginWithEmailAndPassword() {
        // TODO Lógica para verificar se está logado com a auth db
        viewModelScope.launch {
            delay(300)
            authenticated.value = true
        }
    }
}
