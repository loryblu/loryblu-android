package com.odisby.feature.dashboard.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.odisby.feature.dashboard.ui.DashboardScreen
import com.odisby.feature.dashboard.ui.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.dashboardRoute(
    navigateToLogbook: () -> Unit
) {
    composable(route = Screen.Dashboard.route) {
        val viewModel: DashboardViewModel = koinViewModel()
        val usersData by viewModel.usesData.collectAsState()
        viewModel.getUsesData()

        DashboardScreen(
            usesData = usersData,
            navigateToLogbook = navigateToLogbook,
        )
    }
}
