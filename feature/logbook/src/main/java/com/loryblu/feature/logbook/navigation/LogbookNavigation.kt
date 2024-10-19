package com.loryblu.feature.logbook.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.util.Screen
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.logbook.ui.home.LogbookHomeViewModel
import com.loryblu.feature.logbook.ui.home.LogbookScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskCategoryScreen
import com.loryblu.feature.logbook.ui.task.LogbookTaskViewModel
import com.loryblu.feature.logbook.ui.task.create.CreateShiftScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskSummaryScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskScreen
import com.loryblu.feature.logbook.ui.task.edit.EditCategoryScreen
import com.loryblu.feature.logbook.ui.task.edit.EditTaskScreen
import com.loryblu.feature.logbook.ui.task.edit.EditTaskSummaryScreen
import com.loryblu.feature.logbook.ui.task.edit.EditionConfirmedScreen
import com.loryblu.feature.logbook.ui.task.edit.LogbookEditTaskViewModel
import com.loryblu.feature.logbook.ui.webview.WebViewScreen
import com.loryblu.feature.logbook.utils.getNameOfDaySelected
import com.loryblu.feature.logbook.utils.intToShiftString
import org.koin.androidx.compose.getViewModel
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
                navArgument("UPDATE_ANIMATION") {
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

                val hasUpdateInTaskList =
                    backStack.arguments?.getBoolean("UPDATE_ANIMATION") ?: false

                if (hasUpdateInTaskList) {
                    viewModel.selectADayOfWeek(
                        viewModel.lastDayOfWeek,
                        viewModel.lastShift,
                        force = true
                    )
                }
            }

            LogbookScreen(
                onBackButtonClicked = onBackButtonClicked,
                onNextScreenClicked = { navController.navigate(Screen.CreateCategoryScreen.route) },
                onEditTaskClicked = { taskId ->
                    navController.navigate(Screen.EditTaskSummaryScreen.editRoute(taskId))
                },
                userTasks = userTasks.value,
                selectADay = { day, shift ->
                    viewModel.selectADayOfWeek(day, shift)
                },
                onDeleteTaskConfirmed = { logbookTask, deleteOption, selectedDay, shiftSelected ->
                    viewModel.deleteTask(
                        logbookTask = logbookTask,
                        deleteOption = deleteOption,
                        dayOfWeekInt = selectedDay,
                        shift = shiftSelected,
                    )
                },
            )
        }

        navigation(
            startDestination = Screen.CreateCategoryScreen.route,
            route = "register_logbook_task"
        ) {
            composable(route = Screen.CreateCategoryScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateTaskCategoryScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedCategory(it)
                        navController.navigate(Screen.CreateTaskScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.CreateTaskScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateTaskScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedTask(it)
                        navController.navigate(Screen.CreateShiftScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    category = viewModel.getLogbookTaskModel().category,
                )
            }

            composable(route = Screen.CreateShiftScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateShiftScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = { shift, frequency ->
                        viewModel.setShift(shift)
                        viewModel.setFrequency(frequency)
                        navController.navigate(Screen.CreateSummaryScreen.route)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(route = Screen.CreateSummaryScreen.route) {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                val addTaskResult = viewModel.addTaskResult.collectAsState()

                LaunchedEffect(key1 = addTaskResult.value) {
                    when (addTaskResult.value) {
                        is ApiResponse.Success -> {
                            navController.navigate(Screen.Logbook.withUpdateToast()) {
                                popUpTo(Screen.Logbook.route) { inclusive = true }
                            }
                        }

                        is ApiResponse.ErrorDefault -> {
                            navController.navigate(Screen.Logbook.withUpdateToast(success = false)) {
                                popUpTo(Screen.Logbook.route) { inclusive = true }
                            }
                        }

                        else -> {}
                    }

                }

                CreateTaskSummaryScreen(
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
                        navController.navigate(Screen.CreateTaskScreen.route)
                    },
                    onCategoryNavigate = {
                        navController.navigate(Screen.CreateCategoryScreen.route)
                    },
                    onFrequencyChange = {
                        viewModel.setFrequency(getNameOfDaySelected(it))
                    },
                )
            }

            composable(
                route = Screen.EditTaskSummaryScreen.route,
                arguments = listOf(navArgument(name = "TASK_ID") { type = NavType.IntType }),
            ) { backStack ->
                val viewModel: LogbookEditTaskViewModel = getViewModel()
                val taskId = backStack.arguments?.getInt("TASK_ID") ?: 0
                LaunchedEffect(key1 = Unit) {
                    viewModel.getUseTask(taskId)
                }
                val editResult by viewModel.editResult.collectAsState()
                EditTaskSummaryScreen(
                    logbookTaskModel = viewModel.getLogbookTaskModel(),
                    editResult = editResult,
                    onBackButtonClicked = {
                        viewModel.resetLogbookTaskModel()
                        navController.navigate(Screen.Logbook.route) {
                            popUpTo(Screen.Logbook.route) { inclusive = true }
                        }
                    },
                    onShiftChange = {
                        viewModel.setShift(intToShiftString(it))
                    },
                    onTaskNavigate = {
                        navController.navigate(Screen.EditTaskScreen.route)
                    },
                    onCategoryNavigate = {
                        navController.navigate(Screen.EditCategoryScreen.route)
                    },
                    onFrequencyChange = {
                        viewModel.setFrequency(getNameOfDaySelected(it))
                    },
                    onTaskSaveClicked = {
                        viewModel.editLogbookTask {
                            navController.navigate(Screen.EditionConfirmedScreen.route)
                        }
                    }
                )
            }

            composable(route = Screen.EditCategoryScreen.route) {
                val viewModel: LogbookEditTaskViewModel = koinViewModel()


                var cardClicked by rememberSaveable {
                    mutableIntStateOf(viewModel.getLogbookTaskModel().category.idCard)
                }

                EditCategoryScreen(
                    cardClicked = cardClicked,
                    onCardClick = { categoryId ->
                        cardClicked = categoryId
                    },
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedCategory(it)
                        navController.navigate(Screen.EditTaskScreen.route)
                    }
                )
            }

            composable(route = Screen.EditTaskScreen.route) {
                val viewModel: LogbookEditTaskViewModel = koinViewModel()

                val category = viewModel.getLogbookTaskModel().category
                val taskItems = TaskItem.getAllTaskItems().filter { it.category == category }
                val task = viewModel.getLogbookTaskModel().task

                var cardClicked by rememberSaveable {
                    mutableIntStateOf(taskItems.find { it.taskId == task }?.idCard ?: -1)
                }

                EditTaskScreen(
                    taskItems = taskItems,
                    cardClicked = cardClicked,
                    onCardClick = { cardClicked = it },
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedTask(it)
                        val taskId = viewModel.getLogbookTaskModel().taskId
                        navController.navigate(Screen.EditTaskSummaryScreen.editRoute(taskId))
                    }
                )
            }
        }

        composable(route = Screen.EditionConfirmedScreen.route) {
            EditionConfirmedScreen(
                navigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.Logbook.withUpdateToast())
                },
                shouldGoToNextScreen = true
            )
        }

        composable(
            route = Screen.WebViewScreen.route,
            arguments = listOf(
                navArgument("URL") {
                    type = NavType.StringType
                },
            )
        ) { backStack ->
            WebViewScreen(url = backStack.arguments?.getString("URL") ?: "")
        }
    }
}