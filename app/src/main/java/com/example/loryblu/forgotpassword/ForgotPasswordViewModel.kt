package com.example.loryblu.forgotpassword

import androidx.lifecycle.ViewModel
import com.example.loryblu.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class ForgotPasswordUiState(
    val email: String = "",
    val emailState: EmailState = EmailState.LOADING
)

class ForgotPasswordViewModel constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
        }
    }

    fun emailState(email: String) {
        when {
            "" == email.trim() -> {
                _uiState.update {
                    it.copy(emailState = EmailState.EMPTY)
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
            else -> {
                _uiState.update {
                    it.copy(emailState = EmailState.NONE)
                }
            }
        }

    }

    /**
     * Verified if the e-mail has some problem, while search for that on e-mail.
     */
    fun idEmailProblem(): Int? {
        val state = when (_uiState.value.emailState) {
            EmailState.NOT_FOUND -> R.string.email_not_found
            EmailState.SEND -> R.string.email_send
            EmailState.EMPTY -> R.string.empty_email
            EmailState.NONE -> R.string.empty_string
            EmailState.LOADING -> null
        }
        return state
    }
}