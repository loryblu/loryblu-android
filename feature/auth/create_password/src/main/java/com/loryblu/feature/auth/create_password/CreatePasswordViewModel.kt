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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreatePasswordViewModel(
    private val newPassword: NewPasswordApi
) : ViewModel() {

    var shouldGoToNextScreen = mutableStateOf(false)
        private set

    var newPasswordSuccess = mutableStateOf(false)
        private set

    private val passwordHas: Map<Int, Boolean> = mapOf(
        R.string.at_least_eight_characters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.numbers to false,
        R.string.at_least_one_special_character to false
    )

    fun passwordState(password: String) : PasswordInputValid {
        if(password.isBlank()) {
            return PasswordInputValid.Error(R.string.password_is_empty)
        }

        val passwordHas = passwordHas.toMutableMap()

        passwordHas[R.string.at_least_eight_characters] = Regex(".{8,}").containsMatchIn(password)
        passwordHas[R.string.at_least_one_uppercase_letter] = Regex("[A-Z]").containsMatchIn(password)
        passwordHas[R.string.lowercase_letters] = Regex("[a-z]").containsMatchIn(password)
        passwordHas[R.string.numbers] = Regex("[0-9]").containsMatchIn(password)
        passwordHas[R.string.at_least_one_special_character] = Regex("\\W").containsMatchIn(password)

        return if(false in passwordHas.values) {
            PasswordInputValid.ErrorList(passwordHas)
        } else {
            PasswordInputValid.Valid
        }
    }

    fun confirmPasswordState(password : String, confirmPassword: String) : PasswordInputValid {
        if(confirmPassword.isBlank()) {
            return PasswordInputValid.Error(R.string.password_is_empty)
        }

        return if(confirmPassword != password) {
            PasswordInputValid.Error(R.string.passwords_must_be_identical)
        } else {
            PasswordInputValid.Valid
        }
    }
    data class PasswordRequest(val newPassword: Boolean, val message: String? = null)
    private suspend fun passwordRecovery(token: String, password: String): PasswordRequest = withContext(Dispatchers.IO) {
        val params = NewPasswordRequest(
            password = password,
            recoveryToken = token
        )
        val response : ApiResponse = newPassword.newPassword(params)
        if (response == ApiResponse.Success){
            newPasswordSuccess.value = true
            return@withContext PasswordRequest(newPasswordSuccess.value)
        }else{
            // todo ver se o token tá expirado e dar uma mensagem boa
            newPasswordSuccess.value = false
            return@withContext PasswordRequest(newPasswordSuccess.value)
        }
    }

    fun verifyAllConditions(token: String, password: String) {
        viewModelScope.launch {
            val passwordRequest = passwordRecovery(token, password)
            if(passwordRequest.newPassword){
                shouldGoToNextScreen.value = true
            }
        }
    }
}