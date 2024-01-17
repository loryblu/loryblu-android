package com.loryblu.feature.logbook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.ui.LogbookScreen
import com.loryblu.feature.logbook.ui.ShiftScreen

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
                    navController.navigate(Screen.ShiftScreen.route)
                }            )
        }
        composable(route = Screen.ShiftScreen.route) {
            ShiftScreen()
        }
    }
}