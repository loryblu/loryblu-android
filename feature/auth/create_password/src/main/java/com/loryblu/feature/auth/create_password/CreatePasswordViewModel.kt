package com.loryblu.feature.auth.create_password

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.ui.R
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.data.auth.api.NewPasswordApi
import com.loryblu.data.auth.model.NewPasswordRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

data class UiStateCreatePassword(
    val showPassword: Boolean = false,
    val password: String = "",
    val confirmationPassword: String = "",
    val passwordState: PasswordInputValid = PasswordInputValid.Empty,
    val passwordHas: Map<Int, Boolean> = mapOf(
        R.string.at_least_eight_characters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.numbers to false,
        R.string.at_least_one_special_character to false
    ),
    val confirmPasswordState: PasswordInputValid = PasswordInputValid.Empty,
    val newPasswordMessage: String = ""
)

class CreatePasswordViewModel(
    private val newPassword: NewPasswordApi
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiStateCreatePassword())
    val uiState = _uiState

    var shouldGoToNextScreen = mutableStateOf(false)
        private set

    var newPasswordSuccess = mutableStateOf(false)
        private set

    fun updatePassword(newPassword: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(password = newPassword)
            }
        }
    }

    fun updateConfirmationPassword(newConfirmationPassword: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(confirmationPassword = newConfirmationPassword)
            }
        }
    }

    fun passwordCheck() {
        val password = uiState.value.password
        val passwordErrors = _uiState.value.passwordHas.toMutableMap()

        passwordErrors[R.string.at_least_eight_characters] = password.length >= 8
        passwordErrors[R.string.at_least_one_uppercase_letter] = password.any { it.isUpperCase() }
        passwordErrors[R.string.lowercase_letters] = password.any { it.isLowerCase() }
        passwordErrors[R.string.numbers] = password.any { it.isDigit() }
        passwordErrors[R.string.at_least_one_special_character] = password.any { !it.isLetterOrDigit() }

        val passwordState = if (passwordErrors.values.contains(false)) {
            PasswordInputValid.Error(R.string.password_invalid)
        } else {
            PasswordInputValid.Valid
        }

        _uiState.update {
            it.copy(passwordHas = passwordErrors, passwordState = passwordState)
        }
    }
    data class PasswordRequest(val newPassword: Boolean, val message: String)
    private suspend fun passwordRecovery(token: String): PasswordRequest = withContext(Dispatchers.IO) {
        Log.d("recoveryToken", "Token em base64: $token")
        Log.d("password", "passwordRecovery: " + uiState.value.password)
        val params = NewPasswordRequest(
            password = uiState.value.password,
            recoveryToken = token
        )
        val response : ApiResponse = newPassword.newPassword(params)
        if (response.serverStatusCode.value < 300){
            _uiState.update {
                it.copy(newPasswordMessage = response.message[0])
            }
            newPasswordSuccess.value = true
            return@withContext PasswordRequest(newPasswordSuccess.value, response.message[0])
        }else{
            Log.d("newPasswordErrorMessage", "passwordRecovery: " + response.message)
            _uiState.update {
                it.copy(newPasswordMessage = response.message[0])
            }
            newPasswordSuccess.value = false
            return@withContext PasswordRequest(newPasswordSuccess.value, response.message[0])
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

    fun verifyAllConditions(token: String) {
        viewModelScope.launch {
            passwordCheck()
            verifyConfirmationPassword(force = true)
            if(uiState.value.confirmPasswordState == PasswordInputValid.Valid && uiState.value.passwordState == PasswordInputValid.Valid){
                val passwordRequest = runBlocking { passwordRecovery(token) }
                if(passwordRequest.newPassword){
                    shouldGoToNextScreen.value = true
                }
            }
        }
    }
}