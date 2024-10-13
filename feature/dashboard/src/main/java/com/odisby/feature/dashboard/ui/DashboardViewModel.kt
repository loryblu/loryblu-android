package com.odisby.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import com.loryblu.core.network.di.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    private val userSession: UserSession
): ViewModel() {
    private val _childName = MutableStateFlow("")
    val childName = _childName.asStateFlow()

    fun getChildName() {
        val name = userSession.getChildName()
        _childName.value =  name.trim().split("\\s+".toRegex())[0]
    }
}
