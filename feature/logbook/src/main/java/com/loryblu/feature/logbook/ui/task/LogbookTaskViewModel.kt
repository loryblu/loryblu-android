package com.loryblu.feature.logbook.ui.task

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.auth.model.SignInResult
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.model.LogbookTaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogbookTaskViewModel(
    private val session: Session,
    private val logbookTaskModel: LogbookTaskModel,
    private val logbookApi: LogbookApi
) : ViewModel() {

    private val _addTaskResult = MutableStateFlow<ApiResponse>(ApiResponse.Default)
    val addTaskResult = _addTaskResult.asStateFlow()

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

    fun getLogbookTaskModel() : LogbookTaskModel = logbookTaskModel

    fun createLogbookTask() = viewModelScope.launch {
        val childId = session.getChildId()
        val logbookRequest = LogbookTaskRequest(
            childrenId = childId,
            categoryId = logbookTaskModel.task,
            shift = logbookTaskModel.shift,
            frequency = logbookTaskModel.frequency
        )

        logbookApi.createTask(logbookRequest).collect {
            _addTaskResult.value = it
        }
    }
}