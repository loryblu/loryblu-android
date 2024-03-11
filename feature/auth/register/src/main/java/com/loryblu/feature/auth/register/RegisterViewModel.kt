package com.loryblu.feature.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.ui.R
import com.loryblu.core.ui.models.GenderInput
import com.loryblu.core.util.extensions.isEmailValid
import com.loryblu.core.util.validators.BirthdayInputValid
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.core.util.validators.NameInputValid
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.data.auth.api.RegisterApi
import com.loryblu.data.auth.model.RegisterRequest
import com.loryblu.feature.auth.register.model.Children
import com.loryblu.feature.auth.register.model.FinalUserApi
import com.loryblu.feature.auth.register.model.Guardian
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val registerApi: RegisterApi,
    private val finalUserApi: FinalUserApi
) : ViewModel() {
    var shouldGoToNextScreenGuardian = mutableStateOf(false)
    var shouldGoToNextScreenChildren = mutableStateOf(false)

    private val _apiErrorMessage = MutableStateFlow("")
    val apiErrorMessage = _apiErrorMessage.asStateFlow()

    private val passwordHas: Map<Int, Boolean> = mapOf(
        R.string.at_least_eight_characters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.numbers to false,
        R.string.at_least_one_special_character to false
    )

    private suspend fun registerUser(finalUserApi: FinalUserApi): ApiResponse {
        return withContext(Dispatchers.IO) {
            val response: ApiResponse

            val registerRequest = RegisterRequest(
                email = finalUserApi.email.lowercase(),
                password = finalUserApi.password,
                policiesAccepted = finalUserApi.policiesAccepted,
                parentName = finalUserApi.parentName,
                childrenName = finalUserApi.childrenName,
                childrenBirthDate = finalUserApi.childrenBirthDate,
                childrenGender = finalUserApi.childrenGender
            )

            response = registerApi.registerUser(registerRequest)
            return@withContext response
        }
//        viewModelScope.launch {
//            val registerRequest = RegisterRequest(
//                email = finalUserApi.email,
//                password = finalUserApi.password,
//                policiesAccepted = finalUserApi.policiesAccepted,
//                parentName = finalUserApi.parentName,
//                childrenName = finalUserApi.childrenName,
//                childrenBirthDate = "2014-02-28",
//                childrenGender = "MALE"
//            )
//
//            val response = registerApi.registerUser(registerRequest)
//
//            return@launch response
//        }
    }

    fun nameState(name: String) : NameInputValid {
        return when {
            name.isEmpty() -> {
                NameInputValid.Error(R.string.empty_field)
            }
            !name.matches(Regex("^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)+\$", RegexOption.IGNORE_CASE)) -> {
                NameInputValid.Error(R.string.invalid_name)
            }
            name.count { it.isLetter() } < 5 -> {
                NameInputValid.Error(R.string.at_least_five_letters)
            }
            else -> {
                NameInputValid.Valid
            }
        }
    }

    fun genderState(genderInput: GenderInput) : GenderInput {
        return if(genderInput.genderString.isBlank()) {
            GenderInput.Error
        } else {
            genderInput
        }
    }

    fun birthdayState(birthday: String) : BirthdayInputValid {
        return when {
            birthday.isEmpty() -> {
                BirthdayInputValid.Error(R.string.empty_birthday)
            }
            else -> {
                BirthdayInputValid.Valid
            }
        }
    }

    fun verifyAllConditions(children: Children) {
        viewModelScope.launch {
            finalUserApi.run {
                childrenName = children.name
                childrenBirthDate = children.birthday
                childrenGender = children.gender.genderString
                policiesAccepted = children.policiesAccepted
            }

            when(val response = registerUser(finalUserApi)) {
                is ApiResponse.Success -> {
                    shouldGoToNextScreenChildren.value = true
                }
                is ApiResponse.ErrorWithDetail -> {
                    _apiErrorMessage.value = response.detail.details[0].message
                }
                else -> {
                    _apiErrorMessage.value = "Estamos com alguns problemas. Tente novamente mais tarde!"
                }
            }
        }
    }

    fun emailState(email: String) : EmailInputValid {
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

    fun saveGuardian(newGuardian: Guardian) {
        finalUserApi.run {
            email = newGuardian.email
            password = newGuardian.password
            parentName = newGuardian.name
        }
        shouldGoToNextScreenGuardian.value = true
    }
}