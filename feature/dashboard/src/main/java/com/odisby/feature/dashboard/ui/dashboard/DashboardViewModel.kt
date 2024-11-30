package com.odisby.feature.dashboard.ui.dashboard

import androidx.lifecycle.ViewModel
import com.loryblu.core.network.di.UserSession
import com.loryblu.data.auth.UserAuthentication
import com.odisby.feature.dashboard.model.UsesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class DashboardViewModel(
    private val userAuthentication: UserAuthentication,
    private val userSession: UserSession
) : ViewModel() {
    private val _usesData = MutableStateFlow(UsesData())
    val usesData = _usesData.asStateFlow()

    fun getUsesData() = with(userSession) {
        _usesData.value = UsesData(getChildName(), getParentName())
    }

    fun logoutUser() = runBlocking {
        userAuthentication.logoutUser()
    }
}
