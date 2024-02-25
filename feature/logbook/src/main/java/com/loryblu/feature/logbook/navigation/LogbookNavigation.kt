package com.loryblu.feature.logbook.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.ui.task.LogbookTaskViewModel
import com.loryblu.feature.logbook.ui.task.CategoryScreen
import com.loryblu.feature.logbook.ui.home.LogbookScreen
import com.loryblu.feature.logbook.ui.task.ShiftScreen
import com.loryblu.feature.logbook.ui.task.TaskScreen
import com.loryblu.feature.logbook.ui.home.LogbookHomeViewModel
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
            val viewModel: LogbookHomeViewModel = koinViewModel()

            val userTasks = viewModel.userTasks.collectAsState()

            LogbookScreen(
                onBackButtonClicked = onBackButtonClicked,
                onNextScreenClicked = {
                    navController.navigate(Screen.CategoryScreen.route)
                },
                userTasks = userTasks.value,
                selectADayOfWeek = { viewModel.selectADayOfWeek(it) }
            )
        }

        navigation(
            startDestination = Screen.CategoryScreen.route,
            route = "register_logbook_task"
        ) {
            composable(route = Screen.CategoryScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

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
                val viewModel: LogbookTaskViewModel = koinViewModel()

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
                val viewModel: LogbookTaskViewModel = koinViewModel()

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