package com.loryblu.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import kotlinx.coroutines.launch

class HomeViewModel(
    private val session: Session,
    private val navigateToLogin: () -> Unit
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            session.clearToken()
            session.saveRememberLogin(false)
            navigateToLogin()
        }
    }
}