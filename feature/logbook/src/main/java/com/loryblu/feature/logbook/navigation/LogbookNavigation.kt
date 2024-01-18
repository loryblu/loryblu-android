package com.loryblu.feature.logbook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.LogbookViewModel
import com.loryblu.feature.logbook.ui.CategoryScreen
import com.loryblu.feature.logbook.ui.LogbookScreen
import com.loryblu.feature.logbook.ui.ShiftScreen
import com.loryblu.feature.logbook.ui.TaskScreen
import org.koin.androidx.compose.koinViewModel

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

        navigation(
            startDestination = Screen.CategoryScreen.route,
            route = "register_logbook_task"
        ) {
            composable(route = Screen.CategoryScreen.route) {
                val viewModel: LogbookViewModel = koinViewModel()

                CategoryScreen(
                    onBackButtonClicked = { navController.popBackStack() },
                    onNextScreenClicked = {
                        viewModel.setSelectedCategory(it)
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
                val viewModel: LogbookViewModel = koinViewModel()

                TaskScreen(
                    onBackButtonClicked = { navController.popBackStack() },
                    onNextScreenClicked = {
                        viewModel.setSelectedTask(it)
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
                val viewModel: LogbookViewModel = koinViewModel()

                ShiftScreen(
                    onBackButtonClicked = { navController.popBackStack() },
                    onNextScreenClicked = { shift, frequency ->
                        viewModel.setShift(shift)
                        viewModel.setFrequency(frequency)
                        viewModel.createLogbookTask()
                        navController.navigate(Screen.Logbook.route) {
                            launchSingleTop = true
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            launchSingleTop = true
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }
        }
    }
}