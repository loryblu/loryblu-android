package com.loryblu.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.core.ui.R
import com.loryblu.core.util.extensions.isEmailValid
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.data.auth.api.LoginApi
import com.loryblu.data.auth.model.LoginRequest
import com.loryblu.data.auth.model.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginApi: LoginApi,
    private val session: Session
) : ViewModel() {
    private val _authenticated = MutableStateFlow(false)
    val authenticated = _authenticated.asStateFlow()

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
            _signInResult.value = SignInResult.Loading
            try {
                _signInResult.value = loginApi.loginUser(loginRequest)
            } catch (e: Exception) {
                _signInResult.value = SignInResult.Error.stringMessage(
                    e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }

    fun rememberLogin(token: String, rememberLogin: Boolean) {
        viewModelScope.launch {
            session.saveToken(token)
            if (rememberLogin) session.saveRememberLogin(true)
        }
    }
}
