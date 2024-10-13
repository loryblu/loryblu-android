package com.loryblu.feature.logbook.ui.task.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.UserSession
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.extensions.toLogbookTask
import com.loryblu.feature.logbook.model.EditResult
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.useCases.EditTaskUseCase
import com.loryblu.feature.logbook.useCases.GetUserTaskById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogbookEditTaskViewModel(
    private val userSession: UserSession,
    private val editTaskUseCase: EditTaskUseCase,
    private val getUserTaskById: GetUserTaskById,
    private var logbookTaskModel: LogbookTaskModel
) : ViewModel() {

    private val _editResult = MutableStateFlow<EditResult>(EditResult.Loading)
    val editResult = _editResult.asStateFlow()

    fun getLogbookTaskModel() = logbookTaskModel

    fun resetLogbookTaskModel() {
        logbookTaskModel.apply {
            task = ""
            shift = ""
            frequency = listOf()
            taskId = 0
            taskOrder = 0
        }
    }

    fun setSelectedCategory(category: CategoryItem) {
        logbookTaskModel.category = category
    }

    fun setSelectedTask(taskId: String) {
        logbookTaskModel.task = taskId
    }

    fun setShift(shift: String) {
        logbookTaskModel.shift = shift
    }

    fun setFrequency(frequency: List<String>) {
        logbookTaskModel.frequency = frequency
    }

    fun getUseTask(taskApiId: Int) = viewModelScope.launch {
        _editResult.value = EditResult.Loading
        if(logbookTaskModel.taskId == 0) {
            loadTask(taskApiId)
        } else {
            _editResult.value = EditResult.Success
        }
    }

    private suspend fun loadTask(taskApiId: Int) =
        getUserTaskById.invoke(taskApiId).collect { response ->
            if (response is ApiResponseWithData.Success) {
                response.data?.let { logbookTask ->
                    setLogbookTaskModel(logbookTask)
                    _editResult.value = EditResult.Success
                }
            } else {
                _editResult.value = EditResult.Error("Can't loading task")
            }
        }


    private fun setLogbookTaskModel(logbookTask: LogbookTask) {
        logbookTaskModel.apply {
            taskId = logbookTask.id
            taskOrder = logbookTask.order
            category = logbookTask.itemOfCategory.category
            task = logbookTask.itemOfCategory.taskId
            shift = ShiftItem.getShiftItem(logbookTask.shift)
            frequency = logbookTask.frequency
        }
    }

    fun editLogbookTask(onSuccess: () -> Unit) = viewModelScope.launch {
        _editResult.value = EditResult.Loading
        val childId = userSession.getChildId()
        editTaskUseCase.invoke(childId, logbookTaskModel.toLogbookTask()).collect { response ->
            if (response is ApiResponse.Success) {
                _editResult.value = EditResult.Success
                resetLogbookTaskModel()
                onSuccess.invoke()
            } else {
                _editResult.value = EditResult.Error("Can't loading task")
            }
        }
    }
}
