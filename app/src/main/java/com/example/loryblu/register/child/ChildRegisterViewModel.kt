package com.example.loryblu.register.child

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loryblu.R
import com.example.loryblu.util.NameInputValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

data class ChildRegisterUiState(
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
    val isBoyButtonClicked: Boolean = false,
    val isGirlButtonClicked: Boolean = false,
)

class ChildRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChildRegisterUiState())
    val uiState = _uiState
    var shouldGoToNextScreen = mutableStateOf(false)
        private set

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

    fun updateName(newName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(name = newName)
            }
        }
    }

    fun loginWithCorrectName() {
        viewModelScope.launch {
            nameState()
            if (uiState.value.nameState !is NameInputValid.Valid) {
                shouldGoToNextScreen.value = false
                return@launch
            }

            delay(3000)
            shouldGoToNextScreen.value = true
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