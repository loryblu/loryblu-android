package com.example.loryblu.register.child

import androidx.lifecycle.ViewModel
import com.example.loryblu.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class ChildRegisterUiState(
    val name: String = "",
    val nameHas: Map<Int, Boolean> = mapOf(
        R.string.at_least_five_letters to false,
        R.string.Uppercase to false,
        R.string.Lowercase to false,
    ),
    val birthday: String = "",
    val privacyPolicyButtonState: Boolean = false,
    val isBoyButtonClicked: Boolean = false,
    val isGirlButtonClicked: Boolean = false,
)

class ChildRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChildRegisterUiState())
    val uiState = _uiState

    fun nameCheck(newName: String) {
        val _nameHas = _uiState.value.nameHas.toMutableMap()

        _nameHas[R.string.at_least_five_letters] = Regex(".{5,}").containsMatchIn(newName)
        _nameHas[R.string.Uppercase] = Regex("[A-Z]").containsMatchIn(newName)
        _nameHas[R.string.Lowercase] = Regex("[a-z]").containsMatchIn(newName)

        _uiState.update { it.copy(nameHas = _nameHas) }
    }

    fun updateName(newName: String) {
        _uiState.update { it.copy(name = newName) }
    }

    fun updateBirthday(newBirthday: String) {
        _uiState.update { it.copy(birthday = newBirthday) }
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