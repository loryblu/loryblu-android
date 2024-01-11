package com.odisby.feature.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.odisby.feature.dashboard.ui.DashboardScreen

fun NavGraphBuilder.dashboardRoute(
    navigateToLogbook: () -> Unit
) {
    composable(route = Screen.Dashboard.route) {
        DashboardScreen(
            navigateToLogbook = navigateToLogbook
        )
    }
}
