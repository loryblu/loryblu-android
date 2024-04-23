package com.loryblu.feature.logbook.ui.task.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.useCases.GetUserTaskById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LogbookEditTaskViewModel(
    private val session: Session,
    private val logbookApi: LogbookApi,
    private val getUserTaskById: GetUserTaskById,
    private val logbookTaskModel: LogbookTaskModel
) : ViewModel() {

    private var _taskId = MutableStateFlow(0)
    val taskId = _taskId

    fun getLogbookTaskModel() = logbookTaskModel

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
        _taskId.value = taskApiId
        getUserTaskById.invoke(taskApiId).collect { response ->
            if (response is ApiResponseWithData.Success) {
                response.data?.let { t ->
                    logbookTaskModel.apply {
                        category = t.itemOfCategory.category
                        task = t.itemOfCategory.taskId
                        shift = ShiftItem.getShiftItem(t.shift)
                        frequency = t.frequency
                    }
                }
            }
        }
    }

    fun editLogbookTask() = viewModelScope.launch {
        logbookTaskModel.apply {
            val childId = session.getChildId()
            val logbookRequest = LogbookTaskRequest(
                childrenId = childId,
                categoryId = task,
                shift = shift,
                frequency = frequency
            )

            logbookApi.editTask(logbookRequest).collect {
                //_addTaskResult.value = it
            }
        }
    }
}
