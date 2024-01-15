package com.odisby.feature.dashboard.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.odisby.feature.dashboard.ui.DashboardScreen
import com.odisby.feature.dashboard.ui.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.dashboardRoute(
    navigateToLogbook: () -> Unit
) {
    composable(route = Screen.Dashboard.route) {
        val viewModel: DashboardViewModel = koinViewModel()
        val childName by viewModel.childName.collectAsState()
        viewModel.getChildName()

        DashboardScreen(
            navigateToLogbook = navigateToLogbook,
            childName = childName
        )
    }
}
