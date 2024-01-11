package com.odisby.data.dashboard.local

sealed class DashboardItem (
    val idCard: Int,
    val text: Int,
    val drawable: Int,
    val isDisabled: Boolean = false
)