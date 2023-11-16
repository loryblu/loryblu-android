package com.loryblu.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.home.HomeScreen

fun NavGraphBuilder.homeRoute(
    navigateToHomeLogbook: () -> Unit
) {
    composable(route = Screen.Home.route) {
        HomeScreen(
            navigateToHomeLogbook = navigateToHomeLogbook
            )
    }
}
