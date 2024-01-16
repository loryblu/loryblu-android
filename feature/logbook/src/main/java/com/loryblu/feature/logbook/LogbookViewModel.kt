package com.loryblu.feature.logbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import kotlinx.coroutines.launch

class LogbookViewModel(
    private val session: Session,
    private val navigateToLogin: () -> Unit
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            session.clearToken()
            session.saveRememberLogin(false, null)
            navigateToLogin()
        }
    }
}