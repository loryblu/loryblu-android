package com.odisby.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import com.loryblu.core.network.di.Session
import com.odisby.feature.dashboard.model.UsesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    private val session: Session
): ViewModel() {

    private val _usesData = MutableStateFlow(UsesData())
    val usesData = _usesData.asStateFlow()

    fun getUsesData() = with(session) {
        _usesData.value = UsesData(getChildName(), getParentName())
    }
}
