package com.loryblu.feature.logbook.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.model.Category
import com.loryblu.feature.logbook.model.LogbookTaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogbookTaskViewModel(
    private val session: Session,
    private val logbookTaskModel: LogbookTaskModel,
    private val logbookApi: LogbookApi
) : ViewModel() {
    fun setSelectedCategory(category: Category) {
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

    fun createLogbookTask(onResult: (String?, Boolean) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        val childId = session.getChildId()
        val logbookRequest = LogbookTaskRequest(
            childrenId = childId,
            categoryId = logbookTaskModel.task,
            shift = logbookTaskModel.shift,
            frequency = logbookTaskModel.frequency
        )

        logbookApi.createTask(logbookRequest) { responseApi ->
            when(responseApi) {
                is ApiResponseWithData.Success -> {
                    withContext(Dispatchers.Main) {
                        onResult(responseApi.data?.message?.get(0),true)
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        onResult(responseApi.data?.message?.get(0),false)
                    }
                }
            }
        }
    }
}