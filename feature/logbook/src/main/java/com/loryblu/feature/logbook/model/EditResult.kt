package com.loryblu.feature.logbook.model

sealed class EditResult {
    data object Success : EditResult()
    data class Error(val message: String) : EditResult()
    data object Loading : EditResult()
}
