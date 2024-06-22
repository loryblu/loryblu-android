package com.loryblu.feature.logbook.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.ui.task.delete.DeleteOption
import com.loryblu.feature.logbook.ui.task.delete.mutableDialogStateOf
import com.loryblu.feature.logbook.useCases.DeleteTaskUseCase
import com.loryblu.feature.logbook.useCases.GetUserTaskByDayOfWeek
import com.loryblu.feature.logbook.utils.intToDayOfWeek
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogbookHomeViewModel(
    private val getUserTaskByDayOfWeek: GetUserTaskByDayOfWeek,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val session: Session
) : ViewModel() {

    private val _userTasks =
        MutableStateFlow<ApiResponseWithData<List<LogbookTask>>>(ApiResponseWithData.Default())
    val userTasks: StateFlow<ApiResponseWithData<List<LogbookTask>>> = _userTasks

    var lastDayOfWeek = 0
    var lastShift = 0

    val deletedTaskDialogState = mutableDialogStateOf<Pair<LogbookTask, DeleteOption>?>(null)

    fun selectADayOfWeek(dayOfWeekInt: Int, shift: Int, force: Boolean = false) =
        viewModelScope.launch {
            lastDayOfWeek = dayOfWeekInt
            lastShift = shift

            forceGetUserTaskByDayOfWeek(
                dayOfWeekInt = dayOfWeekInt, shift = shift
            )
        }

    fun deleteTask(
        logbookTask: LogbookTask,
        deleteOption: DeleteOption,
        dayOfWeekInt: Int,
        shift: Int,
    ) = viewModelScope.launch {
        deleteTaskUseCase
            .invoke(
                logbookTask = logbookTask,
                deleteOption = deleteOption,
                childrenId = session.getChildId()
            )
            .collect() { response ->
                if (response is ApiResponse.Success) {
                    deletedTaskDialogState.showDialog(
                        Pair(logbookTask, deleteOption)
                    )
                }
            }

        forceGetUserTaskByDayOfWeek(
            dayOfWeekInt = dayOfWeekInt, shift = shift
        )
    }

    private suspend fun forceGetUserTaskByDayOfWeek(
        dayOfWeekInt: Int,
        shift: Int,
    ) {
        getUserTaskByDayOfWeek.invoke(
            dayOfWeek = intToDayOfWeek(dayOfWeekInt),
            shift = shift,
            force = true
        ).collect {
            _userTasks.value = it
        }
    }
}
