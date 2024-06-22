package com.loryblu.feature.logbook.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBContentHome
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.components.FrequencyBar
import com.loryblu.feature.logbook.ui.components.ParentAccessSwitch
import com.loryblu.feature.logbook.ui.components.ShiftBar
import com.loryblu.feature.logbook.ui.components.TaskCardComponent
import com.loryblu.feature.logbook.ui.task.delete.DeleteOption
import com.loryblu.feature.logbook.ui.task.delete.DeleteTaskDialog
import com.loryblu.feature.logbook.ui.task.delete.MutableDialogState
import com.loryblu.feature.logbook.ui.task.delete.DeletedTaskDialog
import com.loryblu.feature.logbook.ui.task.delete.rememberMutableDialogState
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogbookScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: () -> Unit,
    onEditTaskClicked: (taskId: Int) -> Unit,
    onDeleteTaskConfirmed: (
        logbookTask: LogbookTask,
        deleteOption: DeleteOption,
        selectedDay: Int,
        shiftSelected: Int,
    ) -> Unit,
    userTasks: ApiResponseWithData<List<LogbookTask>>,
    selectADay: (Int, Int) -> Unit,
) {

    val data = LocalDate.now()
    val dayOfWeek = data.dayOfWeek.value

    var selectedDay by remember {
        mutableIntStateOf(dayOfWeek)
    }

    var shiftSelected by remember {
        mutableIntStateOf(0)
    }

    var parentAccess by remember { mutableStateOf(true) }

    val deleteTaskDialogState: MutableDialogState<LogbookTask?> =
        rememberMutableDialogState(initialData = null)

    val viewModel: LogbookHomeViewModel = koinViewModel()

    Scaffold(
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.logbook_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onBackButtonClicked() },
                showCloseButton = false
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                TasksSelector(
                    selectedDay = selectedDay,
                    shiftSelected = shiftSelected,
                    onShiftChange = {
                        shiftSelected = it
                        selectADay(selectedDay, shiftSelected)
                    },
                    onSelectedDaysChange = {
                        selectedDay = it
                        selectADay(selectedDay, shiftSelected)
                    }
                )

                Spacer(modifier = Modifier.height(22.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.85f)
                ) {
                    if (userTasks.data.isNullOrEmpty()) {
                        NoAssignmentsLayout(modifier = Modifier.fillMaxSize())
                    } else {
                        ParentAccessSwitch(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            checked = parentAccess,
                            onCheckedClick = {
                                parentAccess = it
                            }
                        )
                        LazyColumn {
                            // Find fix later, but it has tge same id in userTasks?
//                            items(
//                                count = userTasks.data!!.size,
//                                key = {
//                                    userTasks.data!![it].id
//                                },
//                                itemContent = { index ->
//                                    TaskCardComponent(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(16.dp),
//                                        taskItem = userTasks.data!![index],
//                                        parentAccess = parentAccess,
//                                    )
//                                }
//                            )
                            items(userTasks.data!!.size) { index ->
                                TaskCardComponent(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    taskItem = userTasks.data!![index],
                                    parentAccess = parentAccess,
                                    onEditTaskClicked = onEditTaskClicked,
                                    onDeleteTaskClicked = { taskId ->
                                        deleteTaskDialogState.showDialog(userTasks.data!!.find { it.id == taskId })
                                    },
                                )
                            }
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .padding(bottom = 16.dp, end = 16.dp),
                        containerColor = LBContentHome,
                        onClick = {
                            onNextScreenClicked()
                        },
                        shape = ShapeDefaults.ExtraLarge
                    ) {
                        Text(
                            text = stringResource(R.string.new_task),
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = stringResource(R.string.new_task),
                            tint = Color.White
                        )
                    }
                }
            }
            if (deleteTaskDialogState.isVisible.value) {
                deleteTaskDialogState.dialogData.value?.let { logbookTask ->
                    DeleteTaskDialog(
                        task = logbookTask,
                        selectedDay = selectedDay,
                        onDismissRequest = { deleteTaskDialogState.hideDialog() },
                        onConfirmRequest = { deleteOption ->
                            deleteTaskDialogState.hideDialog()
                            onDeleteTaskConfirmed(
                                logbookTask,
                                deleteOption,
                                selectedDay,
                                shiftSelected
                            )
                        },
                    )
                }
            }

            if (viewModel.deletedTaskDialogState.isVisible.value) {
                viewModel.deletedTaskDialogState.dialogData.value?.let { pair ->
                    DeletedTaskDialog(
                        task = pair.first,
                        deleteOption = pair.second,
                        onDismissRequest = { viewModel.deletedTaskDialogState.hideDialog() })
                }
            }
        }
    )
}

@Composable
fun NoAssignmentsLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(com.loryblu.data.logbook.R.drawable.screen_home_logbook),
            contentDescription = null,
            modifier = Modifier
                .width(327.dp)
                .height(302.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Text(
            fontSize = 18.sp,
            text = stringResource(R.string.no_task_found),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    top = 28.dp,
                    end = 9.dp
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TasksSelector(
    selectedDay: Int,
    shiftSelected: Int,
    onSelectedDaysChange: (Int) -> Unit,
    onShiftChange: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FrequencyBar(
            modifier = Modifier.padding(horizontal = 14.dp),
            selectedDay = listOf(selectedDay),
            onDayClicked = {
                if (selectedDay != it) {
                    onSelectedDaysChange(it)
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        ShiftBar(
            modifier = Modifier.padding(horizontal = 10.dp),
            shiftSelected = shiftSelected,
            onShiftChange = {
                if (shiftSelected != it) {
                    onShiftChange(it)
                }
            },
            options = ShiftItem.getShiftItems()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLogbookScreenPreview() {
    LogbookScreen(
        onBackButtonClicked = {},
        onNextScreenClicked = {},
        userTasks = ApiResponseWithData.Default(),
        selectADay = { _, _ -> },
        onEditTaskClicked = {},
        onDeleteTaskConfirmed = { _, _, _, _ -> },
    )
}