package com.loryblu.feature.auth.forgot_password

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.ui.R
import com.loryblu.core.util.extensions.isEmailValid
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.data.auth.api.PasswordRecoveryApi
import com.loryblu.data.auth.model.PasswordRecoveryRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val email: String = "",
    val emailState: EmailInputValid = EmailInputValid.Empty,
    val emailMessage: String = ""
)

class ForgotPasswordViewModel(
    private val passwordRecovery: PasswordRecoveryApi,
): ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState

    var authenticated = mutableStateOf(false)
        private set

    var sendEmailSuccess = mutableStateOf(false)
        private set

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail, emailMessage = "")
        }
    }

    fun emailState() {
        val email = uiState.value.email
        when {
            email.isEmpty() -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Error(R.string.empty_email))
                }
            }
            email.isEmailValid().not() -> {
                sendEmailSuccess.value = false
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

    private fun passwordRecovery() {
        viewModelScope.launch {
            viewModelScope.launch {
                val passwordRecoveryRequest = PasswordRecoveryRequest(
                    email = uiState.value.email.lowercase(),
                )
                val response : ApiResponse = passwordRecovery.passwordRecovery(passwordRecoveryRequest)
                if (response != ApiResponse.ErrorDefault){
                    _uiState.update {
                        it.copy(emailMessage = "E-mail enviado com sucesso")
                    }
                    sendEmailSuccess.value = true
                } else{
                    _uiState.update {
                        it.copy(emailMessage = "Estamos com alguns problemas. Tente novamente mais tarde!")
                    }
                    sendEmailSuccess.value = false
                }
            }
        }
    }
    fun sendEmail() {

        emailState()
        if(uiState.value.emailState is EmailInputValid.Valid) {
            viewModelScope.launch {
                passwordRecovery()
            }
        }
    }
}