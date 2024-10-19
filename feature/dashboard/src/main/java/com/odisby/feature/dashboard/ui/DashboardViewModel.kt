package com.odisby.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import com.loryblu.core.network.di.UserSession
import com.odisby.feature.dashboard.model.UsesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    private val userSession: UserSession
): ViewModel() {
    private val _usesData = MutableStateFlow(UsesData())
    val usesData = _usesData.asStateFlow()

    fun getUsesData() = with(userSession) {
        _usesData.value = UsesData(getChildName(), getParentName())
    }
}
