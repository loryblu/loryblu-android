package com.loryblu.feature.logbook.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.ui.components.LBLoading
import com.loryblu.core.util.Screen
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.logbook.model.EditResult
import com.loryblu.feature.logbook.ui.home.LogbookHomeViewModel
import com.loryblu.feature.logbook.ui.home.LogbookScreen
import com.loryblu.feature.logbook.ui.profile.ChildrenProfileScreen
import com.loryblu.feature.logbook.ui.task.LogbookTaskViewModel
import com.loryblu.feature.logbook.ui.task.create.CreateShiftScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskCategoryScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskScreen
import com.loryblu.feature.logbook.ui.task.create.CreateTaskSummaryScreen
import com.loryblu.feature.logbook.ui.task.edit.EditCategoryScreen
import com.loryblu.feature.logbook.ui.task.edit.EditTaskScreen
import com.loryblu.feature.logbook.ui.task.edit.EditTaskSummaryScreen
import com.loryblu.feature.logbook.ui.task.edit.EditionConfirmedScreen
import com.loryblu.feature.logbook.ui.task.edit.LogbookEditTaskViewModel
import com.loryblu.feature.logbook.ui.webview.WebViewScreen
import com.loryblu.feature.logbook.utils.getNameOfDaySelected
import com.loryblu.feature.logbook.utils.intToShiftString
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

fun NavGraphBuilder.logbookNavigation(
    navController: NavController,
    onBackButtonClicked: () -> Unit
) {

    navigation<Screen.LogbookGraph>(
        startDestination = Screen.Logbook.Home()
    ) {
        composable<Screen.Logbook.Home> { backStackEntry ->
            val logbookArguments: Screen.Logbook.Home = backStackEntry.toRoute()

            val viewModel: LogbookHomeViewModel = koinViewModel()

            val userTasks = viewModel.userTasks.collectAsState()

            LaunchedEffect(key1 = Unit) {
                val data = LocalDate.now()
                val dayOfWeek = data.dayOfWeek.value
                viewModel.selectADayOfWeek(dayOfWeek, 3)

                val hasUpdateInTaskList =
                    logbookArguments.updateAnimation

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
                onNextScreenClicked = { navController.navigate(Screen.Logbook.CreateCategoryScreen) },
                onEditTaskClicked = { taskId ->
                    navController.navigate(Screen.Logbook.EditTaskSummaryScreen(taskId))
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

        navigation<Screen.CreateTaskGraph>(
            startDestination = Screen.Logbook.CreateCategoryScreen,
        ) {
            composable<Screen.Logbook.CreateCategoryScreen> {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateTaskCategoryScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedCategory(it)
                        navController.navigate(Screen.Logbook.CreateTaskScreen)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home) { inclusive = true }
                        }
                    },
                )
            }

            composable<Screen.Logbook.CreateTaskScreen> {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateTaskScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = {
                        viewModel.setSelectedTask(it)
                        navController.navigate(Screen.Logbook.CreateShiftScreen)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home()) { inclusive = true }
                        }
                    },
                    category = viewModel.getLogbookTaskModel().category,
                )
            }

            composable<Screen.Logbook.CreateShiftScreen> {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                CreateShiftScreen(
                    onBackButtonClicked = { navController.navigateUp() },
                    onNextScreenClicked = { shift, frequency ->
                        viewModel.setShift(shift)
                        viewModel.setFrequency(frequency)
                        navController.navigate(Screen.Logbook.CreateSummaryScreen)
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home()) { inclusive = true }
                        }
                    },
                )
            }

            composable<Screen.Logbook.CreateSummaryScreen> {
                val viewModel: LogbookTaskViewModel = koinViewModel()

                val addTaskResult = viewModel.addTaskResult.collectAsState()

                LaunchedEffect(key1 = addTaskResult.value) {
                    when (addTaskResult.value) {
                        is ApiResponse.Success -> {
                            navController.navigate(
                                Screen.Logbook.Home(
                                    updateAnimation = true,
                                    successAdd = true
                                )
                            ) {
                                popUpTo(Screen.Logbook.Home()) { inclusive = true }
                            }
                        }

                        is ApiResponse.ErrorDefault -> {
                            navController.navigate(
                                Screen.Logbook.Home(
                                    updateAnimation = true,
                                    successAdd = false
                                )
                            ) {
                                popUpTo(Screen.Logbook.Home()) { inclusive = true }
                            }
                        }

                        else -> {}
                    }

                }

                CreateTaskSummaryScreen(
                    onBackButtonClicked = {
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home()) { inclusive = true }
                        }
                    },
                    onCloseButtonClicked = {
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home()) { inclusive = true }
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
                        navController.navigate(Screen.Logbook.CreateTaskScreen)
                    },
                    onCategoryNavigate = {
                        navController.navigate(Screen.Logbook.CreateCategoryScreen)
                    },
                    onFrequencyChange = {
                        viewModel.setFrequency(getNameOfDaySelected(it))
                    },
                )

                if (addTaskResult.value == ApiResponse.Loading) {
                    LBLoading()
                }
            }

            composable<Screen.Logbook.EditTaskSummaryScreen> { backStackEntry ->
                val editTaskSummaryArguments =
                    backStackEntry.toRoute<Screen.Logbook.EditTaskSummaryScreen>()

                val viewModel: LogbookEditTaskViewModel = koinViewModel()
                val taskId = editTaskSummaryArguments.taskId
                LaunchedEffect(key1 = Unit) {
                    viewModel.getUseTask(taskId)
                }
                val editResult by viewModel.editResult.collectAsState()
                EditTaskSummaryScreen(
                    logbookTaskModel = viewModel.getLogbookTaskModel(),
                    editResult = editResult,
                    onBackButtonClicked = {
                        viewModel.resetLogbookTaskModel()
                        navController.navigate(Screen.Logbook.Home()) {
                            popUpTo(Screen.Logbook.Home()) { inclusive = true }
                        }
                    },
                    onShiftChange = {
                        viewModel.setShift(intToShiftString(it))
                    },
                    onTaskNavigate = {
                        navController.navigate(Screen.Logbook.EditTaskScreen)
                    },
                    onCategoryNavigate = {
                        navController.navigate(Screen.Logbook.EditCategoryScreen)
                    },
                    onFrequencyChange = {
                        viewModel.setFrequency(getNameOfDaySelected(it))
                    },
                    onTaskSaveClicked = {
                        viewModel.editLogbookTask {
                            navController.navigate(Screen.Logbook.EditionConfirmedScreen)
                        }
                    }
                )

                if (editResult == EditResult.Loading) {
                    LBLoading()
                }
            }

            composable<Screen.Logbook.EditCategoryScreen> {
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
                        navController.navigate(Screen.Logbook.EditTaskScreen)
                    }
                )
            }

            composable<Screen.Logbook.EditTaskScreen> {
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
                        navController.navigate(Screen.Logbook.EditTaskSummaryScreen(taskId))
                    }
                )
            }
        }

        composable<Screen.Logbook.EditionConfirmedScreen> {
            EditionConfirmedScreen(
                navigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(
                        Screen.Logbook.Home(
                            updateAnimation = true,
                            successAdd = true
                        )
                    )
                },
                shouldGoToNextScreen = true
            )
        }

        composable<Screen.Menu.WebViewScreen> { backStackEntry ->
            val webViewArguments = backStackEntry.toRoute<Screen.Menu.WebViewScreen>()

            WebViewScreen(
                url = webViewArguments.url,
                title = webViewArguments.title,
                onCloseClick = { navController.popBackStack() }
            )
        }

        composable<Screen.Menu.ChildrenProfileScreen> {
            ChildrenProfileScreen()
        }
    }
}