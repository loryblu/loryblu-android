package com.example.loryblu.register.guardian

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.loryblu.R
import com.example.loryblu.util.EmailInputValid
import com.example.loryblu.util.NameInputValid
import com.example.loryblu.util.PasswordInputValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class GuardianRegisterUiState(
    val name: String = "",
    val email: String = "",
    val emailState: EmailInputValid = EmailInputValid.Empty,
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
    val nameState: NameInputValid = NameInputValid.Empty,
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

    fun nameState() {
        val name = uiState.value.name
        when {
            name.isEmpty() -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.empty_name))
                }
            }
            !name.matches(Regex("^[A-Z][a-zA-ZÀ-ÖØ-öø-ÿ ]+\$")) -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.invalid_name))
                }
            }
            name.count { it.isLetter() } < 5 -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.at_least_five_letters))
                }
            }
            name.contains("  ") -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.invalid_name))
                }
            }
            else -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Valid)
                }
            }
        }
    }

    fun emailState() {
        val email = uiState.value.email
        val state : EmailInputValid = when {
            email.isEmpty() -> {
                EmailInputValid.Error(R.string.empty_email)
            }

            email.matches(Regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\$")).not() -> {
                EmailInputValid.Error(R.string.invalid_e_mail)
            }

            else -> {
                EmailInputValid.Valid
            }
        }
        Log.d("GuardianRegisterViewModel", "EmailState: $state")
        _uiState.update {
            it.copy(emailState = state)
        }
    }
}
