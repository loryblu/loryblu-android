package com.loryblu.feature.logbook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.ui.CategoryScreen
import com.loryblu.feature.logbook.ui.LogbookScreen
import com.loryblu.feature.logbook.ui.ShiftScreen
import com.loryblu.feature.logbook.ui.TaskScreen

fun NavGraphBuilder.logbookNavigation(
    navController: NavController,
    onBackButtonClicked: () -> Unit
) {
    navigation(
        startDestination = Screen.Logbook.route,
        route = "logbook"
    ) {
        composable(route = Screen.Logbook.route) {
            LogbookScreen(
                onBackButtonClicked = onBackButtonClicked,
                onNextScreenClicked = {
                    navController.navigate(Screen.CategoryScreen.route)
                })
        }

        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen(
                onBackButtonClicked = { navController.popBackStack() },
                onNextScreenClicked = {
                    navController.popBackStack()
                    navController.navigate(Screen.TaskScreen.route)
                },
                onCloseButtonClicked = {
                    navController.popBackStack()
                    navController.navigate(Screen.Logbook.route)
                },
            )
        }

        composable(route = Screen.TaskScreen.route) {
            TaskScreen(
                onBackButtonClicked = { navController.popBackStack() },
                onNextScreenClicked = {
                    navController.popBackStack()
                    navController.navigate(Screen.ShiftScreen.route)
                },
                onCloseButtonClicked = {
                    navController.popBackStack()
                    navController.navigate(Screen.Logbook.route)
                },
            )
        }

        composable(route = Screen.ShiftScreen.route) {
            ShiftScreen(
                onBackButtonClicked = { navController.popBackStack() },
                onNextScreenClicked = {
                    navController.navigate(Screen.Logbook.route) {
                        launchSingleTop = true
                        popUpTo(Screen.ShiftScreen.route) { inclusive = true }
                    }
                },
                onCloseButtonClicked = {
                    navController.popBackStack()
                    navController.navigate(Screen.Logbook.route)
                },
            )
        }
    }
}