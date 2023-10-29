package com.loryblu.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.feature.home.HomeScreen
import com.loryblu.core.util.Screen
import com.loryblu.feature.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.homeRoute(navigateToLogin: () -> Unit) {
    composable(route = Screen.Home.route) {
        val viewModel: HomeViewModel = koinViewModel {
            parametersOf(navigateToLogin)
        }
        HomeScreen(viewModel = viewModel)
    }
}
