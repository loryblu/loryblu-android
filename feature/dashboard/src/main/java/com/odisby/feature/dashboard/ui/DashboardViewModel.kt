package com.odisby.feature.dashboard.ui

import androidx.lifecycle.ViewModel
import com.loryblu.core.network.di.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    private val session: Session
): ViewModel() {
    private val _childName = MutableStateFlow("")
    val childName = _childName.asStateFlow()

    fun getChildName() {
        val name = session.getChildName()
        _childName.value =  name.trim().split("\\s+".toRegex())[0]
    }
}