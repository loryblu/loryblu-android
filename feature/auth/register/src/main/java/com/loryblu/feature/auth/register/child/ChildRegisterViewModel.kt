package com.loryblu.feature.auth.register.child

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
import com.loryblu.feature.auth.register.util.Children
import com.loryblu.feature.auth.register.util.FinalUserApi
import com.loryblu.feature.auth.register.util.Guardian
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ChildRegisterUiState(
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
        R.string.at_least_eight_characters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.numbers to false,
        R.string.at_least_one_special_character to false
    ),
    val nameState: NameInputValid = NameInputValid.Empty,
)

class ChildRegisterViewModel(
    private val registerApi: RegisterApi,
    private val finalUserApi: FinalUserApi
) : ViewModel() {
    var shouldGoToNextScreenGuardian = mutableStateOf(false)
    var shouldGoToNextScreenChildren = mutableStateOf(false)
    var apiErrorMessage = mutableStateOf<String?>(null)

    var uiState = MutableStateFlow(ChildRegisterUiState())
        private set

    private suspend fun registerUser(finalUserApi: FinalUserApi): ApiResponse {
        return withContext(Dispatchers.IO) {
            val response: ApiResponse

            val test = RegisterRequest(
                email = finalUserApi.email,
                password = finalUserApi.password,
                policiesAccepted = finalUserApi.policiesAccepted,
                parentName = finalUserApi.parentName,
                childrenName = finalUserApi.childrenName,
                childrenBirthDate = "2014-02-28",
                childrenGender = "MALE"
            )

            response = registerApi.registerUser(test)
            return@withContext response
        }
    }

    fun nameState(name: String) : NameInputValid {
        return when {
            name.isEmpty() -> {
                NameInputValid.Error(R.string.empty_field)
            }
            !name.matches(Regex("^[A-Z][a-zA-ZÀ-ÖØ-öø-ÿ ]+\$")) -> {
                NameInputValid.Error(R.string.invalid_name)
            }
            name.count { it.isLetter() } < 5 -> {
                NameInputValid.Error(R.string.at_least_five_letters)
            }
            name.contains("  ") -> {
                NameInputValid.Error(R.string.invalid_name)
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

            val response = registerUser(finalUserApi)

            if(response.statusCode == null) {
                shouldGoToNextScreenChildren.value = true
                apiErrorMessage.value = null
            } else {
                apiErrorMessage.value = response.message[0]
            }
        }
    }

//    private fun childrenStatesCheck(children: Children) : Boolean {
//        val childrenNameState = nameState(children.name)
//        val childrenBirthdayState = birthdayState(children.birthday)
//        val childrenGenderState = genderState(children.gender)
//
//        return (
//                childrenNameState == NameInputValid.Valid
//                && childrenBirthdayState == BirthdayInputValid.Valid
//                && (childrenGenderState == GenderInput.MALE || childrenGenderState == GenderInput.FEMALE)
//                )
//    }

    fun privacyState(privacy: Boolean): Boolean {
        return privacy
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

    fun emailStateGurdian() {
        val email = uiState.value.email
        val state : EmailInputValid = when {
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
        uiState.update {
            it.copy(emailState = state)
        }
    }

    fun passwordState(password: String) {
        val passwordHas = uiState.value.passwordHas.toMutableMap()

        passwordHas[R.string.at_least_eight_characters] = Regex(".{8,}").containsMatchIn(password)
        passwordHas[R.string.at_least_one_uppercase_letter] = Regex("[A-Z]").containsMatchIn(password)
        passwordHas[R.string.lowercase_letters] = Regex("[a-z]").containsMatchIn(password)
        passwordHas[R.string.numbers] = Regex("[0-9]").containsMatchIn(password)
        passwordHas[R.string.at_least_one_special_character] = Regex("\\W").containsMatchIn(password)

        val passwordState: PasswordInputValid = if (false in passwordHas.values) {
            PasswordInputValid.EmptyError
        } else {
            PasswordInputValid.Valid
        }

        uiState.update {
            it.copy(passwordHas = passwordHas, passwordState = passwordState)
        }
    }

    fun confirmPasswordState(force: Boolean = false, password : String, confirmPassword: String) {
        if(!force && confirmPassword.isEmpty()) return

        val state = if(confirmPassword != password) {
            PasswordInputValid.Error(R.string.passwords_must_be_identical)
        } else {
            PasswordInputValid.Valid
        }

        uiState.update {
            it.copy(confirmPasswordState = state)
        }
    }


    /**
     * aaaaaaa
     */


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
                    it.copy(nameState = NameInputValid.Error(R.string.empty_field))
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

    fun verifyAllConditions() {
        viewModelScope.launch {
            passwordState(uiState.value.password)
            confirmPasswordState(force = true)
            emailState(uiState.value.email)
            nameState()

            if(uiState.value.confirmPasswordState == PasswordInputValid.Valid &&
                uiState.value.passwordState == PasswordInputValid.Valid &&
                uiState.value.nameState == NameInputValid.Valid &&
                uiState.value.emailState == EmailInputValid.Valid
            ){
                shouldGoToNextScreenGuardian.value = true
            }
        }
    }

    fun saveGuardian(newGuardian: Guardian) {
        finalUserApi.run {
            email = newGuardian.email
            password = newGuardian.password
            parentName = newGuardian.name
        }
    }

}