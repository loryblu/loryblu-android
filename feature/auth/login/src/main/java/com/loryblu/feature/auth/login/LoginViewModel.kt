package com.loryblu.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.ui.R
import com.loryblu.core.util.extensions.isEmailValid
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.data.auth.UserAuthentication
import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userAuthentication: UserAuthentication
) : ViewModel() {

    private val _signInResult = MutableStateFlow<SignInResult>(SignInResult.Empty)
    val signInResult = _signInResult.asStateFlow()

    fun emailState(email: String): EmailInputValid {
        return when {
            email.isEmpty() -> {
                EmailInputValid.Error(R.string.empty_email)
            }

            email.isEmailValid().not() -> {
                EmailInputValid.Error(R.string.invalid_field)
            }

            else -> {
                EmailInputValid.Valid
            }
        }
    }

    fun passwordState(password: String): PasswordInputValid {
        return when {
            password.isEmpty() -> {
                PasswordInputValid.Error(R.string.password_is_empty)
            }

            else -> {
                PasswordInputValid.Valid
            }
        }
    }

    fun loginWithEmailAndPassword(loginRequest: LoginRequest) {
        viewModelScope.launch {
            userAuthentication.loginWithEmailAndPassword(loginRequest).collect {
                _signInResult.value = it
            }
        }
    }

}
