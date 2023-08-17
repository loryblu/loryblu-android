package com.example.loryblu.login

import android.util.Log
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
    val emailState: EmailInputValid = EmailInputValid.Empty,
    val password: String = "",
    val passwordState: PasswordInputValid = PasswordInputValid.Empty,
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
                    it.copy(emailState = EmailInputValid.Error(R.string.empty_email))
                }
            }
            email.isEmailValid().not() -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Error(R.string.invalid_e_mail))
                }
            }
            else -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Valid)
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

    fun passwordState(newPassword: String) {
        when {
            newPassword.isEmpty() -> {
                _uiState.update {
                    it.copy(passwordState = PasswordInputValid.Error(R.string.password_is_empty))
                }
            }
            else -> {
                _uiState.update {
                    it.copy(passwordState = PasswordInputValid.Valid)
                }
            }
        }
    }

    fun loginWithEmailAndPassword() {
        viewModelScope.launch {
            // Essa verificação mudará para uma verificação com a db
            if(uiState.value.passwordState is PasswordInputValid.Valid && uiState.value.emailState is EmailInputValid.Valid){
                delay(300)
                authenticated.value = true
            }
           else {
               authenticated.value = false
               Log.d("LoginViewModel", "Password or email is not valid")
            }
        }
    }
}
