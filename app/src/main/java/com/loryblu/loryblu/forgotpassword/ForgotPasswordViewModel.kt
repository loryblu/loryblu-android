package com.loryblu.loryblu.forgotpassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.loryblu.R
import com.loryblu.loryblu.login.isEmailValid
import com.loryblu.loryblu.util.EmailInputValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val email: String = "",
    val emailState: EmailInputValid = EmailInputValid.Empty
)

class ForgotPasswordViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState

    var authenticated = mutableStateOf(false)
        private set

    var sendEmailSuccess = mutableStateOf(false)
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

    fun sendEmail() {
        // TODO Aqui será feita a requição para a db com o email passado

        emailState()
        if(uiState.value.emailState is EmailInputValid.Valid) {
            viewModelScope.launch {
                delay(1000)
                sendEmailSuccess.value = true
                delay(5000)
                authenticated.value = true
            }
        }
    }
}