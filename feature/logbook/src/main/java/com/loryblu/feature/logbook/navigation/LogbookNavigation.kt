package com.loryblu.feature.logbook.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.ui.home.LogbookHomeViewModel
import com.loryblu.feature.logbook.ui.home.LogbookScreen
import com.loryblu.feature.logbook.ui.task.CategoryScreen
import com.loryblu.feature.logbook.ui.task.LogbookTaskViewModel
import com.loryblu.feature.logbook.ui.task.ShiftScreen
import com.loryblu.feature.logbook.ui.task.SummaryScreen
import com.loryblu.feature.logbook.ui.task.TaskScreen
import com.loryblu.feature.logbook.utils.getNameOfDaySelected
import com.loryblu.feature.logbook.utils.intToShiftString
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

fun NavGraphBuilder.logbookNavigation(
    navController: NavController,
    onBackButtonClicked: () -> Unit
) {
    navigation(
        startDestination = Screen.Logbook.route,
        route = "logbook"
    ) {
        composable(
            route = Screen.Logbook.route,
            arguments = listOf(
                navArgument("ADDED_ANIMATION") {
                    defaultValue = false
                    type = NavType.BoolType
                },
                navArgument("SUCCESS_ADD") {
                    defaultValue = false
                    type = NavType.BoolType
                },
            )
        ) { backStack ->
            val viewModel: LogbookHomeViewModel = koinViewModel()

            val userTasks = viewModel.userTasks.collectAsState()

            LaunchedEffect(key1 = Unit) {
                val data = LocalDate.now()
                val dayOfWeek = data.dayOfWeek.value
                viewModel.selectADayOfWeek(dayOfWeek, 3)

                val hasAddedANewTask = backStack.arguments?.getBoolean("ADDED_ANIMATION") ?: false

                if (hasAddedANewTask) {
                    viewModel.selectADayOfWeek(
                        viewModel.lastDayOfWeek,
                        viewModel.lastShift,
                        force = true
                    )
                }
            }

            LogbookScreen(
                onBackButtonClicked = onBackButtonClicked,
                onNextScreenClicked = { navController.navigate(Screen.CategoryScreen.route) },
                userTasks = userTasks.value,
                selectADay = { day, shift ->
                    viewModel.selectADayOfWeek(day, shift)
                },
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
                        navController.navigate(Screen.Logbook.route) {
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
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    category = viewModel.getLogbookTaskModel().category,
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
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.SummaryScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                val addTaskResult = viewModel.addTaskResult.collectAsState()

                LaunchedEffect(key1 = addTaskResult.value) {
                    when (addTaskResult.value) {
                        is ApiResponse.Success -> {
                            navController.navigate(Screen.Logbook.withAddedToast()) {
                                popUpTo(Screen.Logbook.route) { inclusive = true }
                            }
                        }

                        is ApiResponse.ErrorDefault -> {
                            navController.navigate(Screen.Logbook.withAddedToast(success = false)) {
                                popUpTo(Screen.Logbook.route) { inclusive = true }
                            }
                        }

                        else -> {}
                    }

                }

                SummaryScreen(
                    onBackButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    logbookTaskModel = viewModel.getLogbookTaskModel(),
                    onNextScreenClicked = {
                        viewModel.createLogbookTask()
                    },
                    onShiftChange = {
                        viewModel.setShift(intToShiftString(it))
                    },
                    onTaskNavigate = {
                        navController.navigate(Screen.TaskScreen.route)
                    },
                    onCategoryNavigate = {
                        navController.navigate(Screen.CategoryScreen.route)
                    },
                    onFrequencyChange = {
                        viewModel.setFrequency(getNameOfDaySelected(it))
                    },
                )
            }
        }
    }
}