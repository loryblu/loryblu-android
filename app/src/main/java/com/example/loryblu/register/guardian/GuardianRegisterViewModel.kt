package com.example.loryblu.register.guardian

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import com.example.loryblu.login.isEmailValid
import com.example.loryblu.util.EmailInputValid
import com.example.loryblu.util.NameInputValid
import com.example.loryblu.util.PasswordInputValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
    var uiState = MutableStateFlow(GuardianRegisterUiState())
        private set

    var shouldGoToNextScreen = mutableStateOf(false)
        private set

    fun passwordState() {
        val password = uiState.value.password
        val passwordHas = uiState.value.passwordHas.toMutableMap()

        passwordHas[R.string.MoreThanEight] = Regex(".{8,}").containsMatchIn(password)
        passwordHas[R.string.Uppercase] = Regex("[A-Z]").containsMatchIn(password)
        passwordHas[R.string.Lowercase] = Regex("[a-z]").containsMatchIn(password)
        passwordHas[R.string.Numbers] = Regex("[0-9]").containsMatchIn(password)
        passwordHas[R.string.SpecialCharacters] = Regex("\\W").containsMatchIn(password)

        val passwordState: PasswordInputValid = if (false in passwordHas.values) {
            PasswordInputValid.EmptyError
        } else {
            PasswordInputValid.Valid
        }

        uiState.update {
            it.copy(passwordHas = passwordHas, passwordState = passwordState)
        }
    }

    fun confirmPasswordState(force: Boolean = false) {
        val confirmPassword = uiState.value.confirmationPassword

        if(!force && confirmPassword.isEmpty()) return

        val state = if(confirmPassword != uiState.value.password) {
            PasswordInputValid.Error(R.string.passwords_must_be_identical)
        } else {
            PasswordInputValid.Valid
        }

        uiState.update {
            it.copy(confirmPasswordState = state)
        }
    }

    fun updateName(newName: String) {
        uiState.update {
            it.copy(name = newName)
        }
    }

    fun updateEmail(newEmail: String) {
        uiState.update {
            it.copy(email = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        uiState.update {
            it.copy(password = newPassword)
        }
    }

    fun updateConfirmationPassword(newConfirmationPassword: String) {
        uiState.update {
            it.copy(confirmationPassword = newConfirmationPassword)
        }
    }

    fun nameState() {
        val name = uiState.value.name
        when {
            name.isEmpty() -> {
                uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.empty_name))
                }
            }
            !name.matches(Regex("^[A-Z][a-zA-ZÀ-ÖØ-öø-ÿ ]+\$")) -> {
                uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.invalid_name))
                }
            }
            name.count { it.isLetter() } < 5 -> {
                uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.at_least_five_letters))
                }
            }
            name.contains("  ") -> {
                uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.invalid_name))
                }
            }
            else -> {
                uiState.update {
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
            email.isEmailValid().not() -> {
                EmailInputValid.Error(R.string.invalid_e_mail)
            }
            else -> {
                EmailInputValid.Valid
            }
        }
        Log.d("GuardianRegisterViewModel", "EmailState: $state")
        uiState.update {
            it.copy(emailState = state)
        }
    }

    fun verifyAllConditions() {
        viewModelScope.launch {
            passwordState()
            confirmPasswordState(force = true)
            emailState()
            nameState()

            if(uiState.value.confirmPasswordState == PasswordInputValid.Valid &&
                uiState.value.passwordState == PasswordInputValid.Valid &&
                uiState.value.nameState == NameInputValid.Valid &&
                uiState.value.emailState == EmailInputValid.Valid
            ){
                delay(2000)
                shouldGoToNextScreen.value = true
            }
        }
    }
}
