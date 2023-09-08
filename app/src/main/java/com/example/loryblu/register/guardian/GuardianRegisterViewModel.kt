package com.example.loryblu.register.guardian

import androidx.lifecycle.ViewModel
import com.example.loryblu.R
import com.example.loryblu.util.PasswordInputValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

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
    private val _uiState = MutableStateFlow(GuardianRegisterUiState())
    val uiState = _uiState

    fun passwordCheck(newPassword: String) {
        val passwordHas = _uiState.value.passwordHas.toMutableMap()

        passwordHas[R.string.MoreThanEight] = Regex(".{8,}").containsMatchIn(newPassword)
        passwordHas[R.string.Uppercase] = Regex("[A-Z]").containsMatchIn(newPassword)
        passwordHas[R.string.Lowercase] = Regex("[a-z]").containsMatchIn(newPassword)
        passwordHas[R.string.Numbers] = Regex("[0-9]").containsMatchIn(newPassword)
        passwordHas[R.string.SpecialCharacters] = Regex("\\W").containsMatchIn(newPassword)

        val passwordState: PasswordInputValid = if (false in passwordHas.values) {
            PasswordInputValid.EmptyError
        } else {
            PasswordInputValid.Valid
        }

        _uiState.update {
            it.copy(passwordHas = passwordHas, passwordState = passwordState)
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
