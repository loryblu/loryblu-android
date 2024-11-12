package com.odisby.feature.dashboard.model

data class UsesData(
    val childFullName: String = "",
    val parentFullName: String = "",
) {
    val childFirstName = childFullName.trim().split("\\s+".toRegex()).first()
}
