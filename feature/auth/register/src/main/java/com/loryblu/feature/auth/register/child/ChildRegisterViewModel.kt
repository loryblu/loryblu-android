package com.loryblu.feature.auth.register.child

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.ui.R
import com.loryblu.core.util.validators.BirthdayInputValid
import com.loryblu.core.util.validators.GenderButtonValid
import com.loryblu.core.util.validators.NameInputValid
import com.loryblu.data.auth.api.RegisterApi
import com.loryblu.data.auth.model.ChildrenGender
import com.loryblu.data.auth.model.RegisterRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

data class ChildRegisterUiState(
    val genderState: GenderButtonValid = GenderButtonValid.Empty,
    val name: String = "",
    val confirmationName: String = "",
    val nameState: NameInputValid = NameInputValid.Empty,
    val nameErrors: Map<Int, Boolean> = mapOf(
        R.string.at_least_five_letters to false,
        R.string.at_least_one_uppercase_letter to false,
        R.string.lowercase_letters to false,
        R.string.at_least_one_special_character to false
    ),
    val confirmNameState: NameInputValid = NameInputValid.Empty,
    val privacyPolicyButtonState: Boolean = false,
    val isBoyButtonClicked: Boolean = false, //mudar para uma só variavel genderSelect: String ("Boy" "Girl")
    val isGirlButtonClicked: Boolean = false,
    val birthday: String = "",
    val birthdayState: BirthdayInputValid = BirthdayInputValid.Empty,
)

class ChildRegisterViewModel(
    private val registerApi: RegisterApi
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChildRegisterUiState())
    val uiState = _uiState
    var shouldGoToNextScreen = mutableStateOf(false)

    private fun registerUser() {
        viewModelScope.launch {
            val test = RegisterRequest(
                email = "test@asfasfasfsafsatest.com",
                password = "Test@123",
                policiesAccepted = true,
                parentName = "André",
                childrenName = "Jeann",
                childrenBirthDate = "2009-02-28",
                childrenGender = ChildrenGender.MALE
            )
            val response = registerApi.registerUser(test)
            Log.d("Resposta mensagem", response.message.toString())
        }
    }

    fun nameState() {
        val name = uiState.value.name
        when {
            name.isEmpty() -> {
                _uiState.update {
                    it.copy(nameState = NameInputValid.Error(R.string.empty_field))
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

    fun genderState() {
        val boy = uiState.value.isBoyButtonClicked
        val girl = uiState.value.isGirlButtonClicked
        if (!boy && !girl){
            uiState.update {
                it.copy(genderState = GenderButtonValid.EmptyError)
            }
        }else{
            uiState.update {
                it.copy(genderState = GenderButtonValid.Valid)
            }
        }
    }
    fun birthdayState() {
        val birthday = uiState.value.birthday
        when {
            birthday.isEmpty() -> {
                uiState.update {
                    it.copy(birthdayState = BirthdayInputValid.Error(R.string.empty_birthday))
                }
            }
            else -> {
                uiState.update {
                    it.copy(birthdayState = BirthdayInputValid.Valid)
                }
            }
        }
    }

    fun updateName(newName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(name = newName)
            }
        }
    }

    fun updateBirthday(newBirthday: String) {
        uiState.update {
            it.copy(birthday = newBirthday)
        }
    }

   /* fun loginWithCorrectName() {
        viewModelScope.launch {
            nameState()
            if (uiState.value.nameState !is NameInputValid.Valid) {
                shouldGoToNextScreen.value = false
                return@launch
            }

            delay(3000)
            shouldGoToNextScreen.value = true
        }
    }*/

    fun verifyAllConditions() {
        viewModelScope.launch {
            nameState()
            birthdayState()
            genderState()
            if (uiState.value.nameState is NameInputValid.Valid &&
                uiState.value.birthdayState is BirthdayInputValid.Valid &&
                (uiState.value.isBoyButtonClicked || uiState.value.isGirlButtonClicked)
                && uiState.value.privacyPolicyButtonState) {

                registerUser()
                delay(3000)
                shouldGoToNextScreen.value = true
            }
        }
    }

    fun updatePrivacyPolicyButtonState(newPrivacyPolicyButtonState: Boolean) {
        _uiState.update { it.copy(privacyPolicyButtonState = newPrivacyPolicyButtonState) }
    }

    fun updateBoyButtonState(newIsClicked: Boolean) {
        _uiState.update { it.copy(isBoyButtonClicked = newIsClicked) }
    }

    fun updateGirlButtonState(newIsClicked: Boolean) {
        _uiState.update { it.copy(isGirlButtonClicked = newIsClicked) }
    }

}