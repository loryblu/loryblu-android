package com.loryblu.feature.auth.forgot_password

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.ui.R
import com.loryblu.core.util.extensions.isEmailValid
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.data.auth.api.PasswordRecoveryApi
import com.loryblu.data.auth.api.RegisterApi
import com.loryblu.data.auth.model.PasswordRecoveryRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val email: String = "",
    val emailState: EmailInputValid = EmailInputValid.Empty,
    val emailErrorMessage: String = ""
)

class ForgotPasswordViewModel(
    private val passwordRecovery: PasswordRecoveryApi
): ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState

    var authenticated = mutableStateOf(false)
        private set

    var sendEmailSuccess = mutableStateOf(false)
        private set

    var sendEmailFailure = mutableStateOf(false)
        private set

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
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
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Error(R.string.invalid_field))
                }
            }
            else -> {
                _uiState.update {
                    it.copy(emailState = EmailInputValid.Valid)
                }
            }
            // TODO
//             procurar o email na api se não encontrar precisa mudar o state para not found
//             o plano é aparecer uma menssagem de carregamento quando voce colocar o email porque
//             vai ser preciso mandar uma requisição para o back-end para ver ser o e-mail existe
//             uma animação de carregamento vai aparecer equanto o e-mail não aparece e depois ele
//             mostrar se o email existe e foi enviado com suceeso ou não existe
//             alem disso ele pode mostrar um erro de conexão com a internet porque sei internet é
//             um problema diferente de o email não existe
        }

    }
    private fun passwordRecovery() {
        viewModelScope.launch {
            viewModelScope.launch {
                val test = PasswordRecoveryRequest(
                    email = uiState.value.email,
                )
                val response : ApiResponse = passwordRecovery.passwordRecovery(test)
                if (response.statusCode == null){
                    sendEmailSuccess.value = true
                    sendEmailFailure.value = false
                }else{
                    _uiState.update {
                        it.copy(emailErrorMessage = response.message[0])
                    }
                    sendEmailSuccess.value = false
                    sendEmailFailure.value = true
                }
                Log.d("Resposta mensagem", response.message.toString())
            }
        }
    }
    fun sendEmail() {

        emailState()
        if(uiState.value.emailState is EmailInputValid.Valid) {
            viewModelScope.launch {
                passwordRecovery()

                //delay(5000)
                //authenticated.value = true
            }
        }
    }
}