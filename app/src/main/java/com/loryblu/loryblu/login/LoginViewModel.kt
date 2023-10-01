package com.loryblu.loryblu.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import com.loryblu.loryblu.util.EmailInputValid
import com.loryblu.loryblu.util.PasswordInputValid
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

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState

    var authenticated = mutableStateOf(false)
        private set

    fun emailState() {
        val email = uiState.value.email
        when {
            email.isEmpty() -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Error(R.string.empty_email))
                }
            }
            email.isEmailValid().not() -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Error(R.string.invalid_field))
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

    fun passwordState() {
        val password = uiState.value.password
        when {
            password.isEmpty() -> {
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
            emailState()
            passwordState()

            if(uiState.value.emailState !is EmailInputValid.Valid || uiState.value.passwordState !is PasswordInputValid.Valid) {
                Log.d("LoginViewModel", "Password or email is not valid")
                authenticated.value = false
                return@launch
            }
            // Verificação na db

            delay(3000)
            authenticated.value = true
        }
    }
}
