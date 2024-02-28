package com.loryblu.feature.logbook.navigation

import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
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
import com.loryblu.feature.logbook.ui.task.DoneScreen
import com.loryblu.feature.logbook.ui.task.SummaryScreen
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
                onNextScreenClicked = { navController.navigate(Screen.CategoryScreen.route) },
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
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedCategory(it)
                        navController.navigate(Screen.TaskScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.TaskScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                TaskScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedTask(it)
                        navController.navigate(Screen.ShiftScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.ShiftScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                ShiftScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = { shift, frequency ->
                        viewModel.setShift(shift)
                        viewModel.setFrequency(frequency)
                        navController.navigate(Screen.SummaryScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.SummaryScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()
                val context = LocalContext.current

                SummaryScreen(
                    onBackButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    logbookTaskModel = viewModel.getLogbookTaskModel()
                ) {
                    viewModel.createLogbookTask { msg, toDoneView ->
                        if(toDoneView) {
                            navController.navigate(Screen.DoneView.route)
                        } else {
                            Toast.makeText(context, msg,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            composable(route = Screen.DoneView.route) {
                DoneScreen(
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}