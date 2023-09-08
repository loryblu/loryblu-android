package com.example.loryblu.register.guardian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import com.example.loryblu.util.PasswordInputValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GuardianRegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordState: PasswordInputValid = PasswordInputValid.Empty,
    val confirmPasswordState: PasswordInputValid = PasswordInputValid.Empty,
    val showPassword: Boolean = true,
    val showConfirmationPassword: Boolean = true,
    val passwordHas: Map<Int, Boolean> = mapOf(
        R.string.MoreThanEight to false,
        R.string.Uppercase to false,
        R.string.Lowercase to false,
        R.string.Numbers to false,
        R.string.SpecialCharacters to false
    ),
)

class GuardianRegisterViewModel : ViewModel() {
    // eu tenho uma duvida de usar isso com flow sera que
    // não estou gerando muitos ojetos e como o CG faz para eliminar esses objetos ?
    private val _uiState = MutableStateFlow(GuardianRegisterUiState())
    val uiState = _uiState

    fun passwordCheck(newPassword: String) {
        val passwordHas = _uiState.value.passwordHas.toMutableMap()

        passwordHas[R.string.MoreThanEight] = Regex(".{8,}").containsMatchIn(newPassword)
        passwordHas[R.string.Uppercase] = Regex("[A-Z]").containsMatchIn(newPassword)
        passwordHas[R.string.Lowercase] = Regex("[a-z]").containsMatchIn(newPassword)
        passwordHas[R.string.Numbers] = Regex("[0-9]").containsMatchIn(newPassword)
        passwordHas[R.string.SpecialCharacters] = Regex("\\W").containsMatchIn(newPassword)

        _uiState.update {
            it.copy(passwordHas = passwordHas)
        }
    }

    fun verifyConfirmationPassword(force: Boolean = false) {
        val confirmPassword = uiState.value.confirmationPassword

        if(!force && confirmPassword.isEmpty()) return

        val state = if(confirmPassword != _uiState.value.password) {
            PasswordInputValid.Error(R.string.passwords_must_be_identical)
        } else {
            PasswordInputValid.Valid
        }

        _uiState.update {
            it.copy(confirmPasswordState = state)
        }
    }

    fun togglePassword() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showPassword = it.showPassword.not())
            }
        }
    }

    fun toggleConfirmationPassword() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showConfirmationPassword = it.showConfirmationPassword.not())
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
            it.copy(email = newEmail)
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
