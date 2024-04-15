package com.loryblu.feature.logbook.ui.task.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.di.Session
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.model.LogbookTaskModel
import kotlinx.coroutines.launch

class LogbookEditTaskViewModel(
    private val session: Session,
    private var logbookTaskModel: LogbookTaskModel,
    private val logbookApi: LogbookApi
) : ViewModel() {

    init {
        logbookTaskModel = LogbookTaskModel(
            category = CategoryItem.Routine,
            task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
            shift = "morning",
            frequency = listOf("sun", "mon")
        )
    }

    fun getLogbookTaskModel() : LogbookTaskModel = logbookTaskModel

    fun setShift(shift: String) {
        logbookTaskModel.shift = shift
    }

    fun setFrequency(frequency: List<String>) {
        logbookTaskModel.frequency = frequency
    }

    fun editLogbookTask() = viewModelScope.launch {
        val childId = session.getChildId()
        val logbookRequest = LogbookTaskRequest(
            childrenId = childId,
            categoryId = logbookTaskModel.task,
            shift = logbookTaskModel.shift,
            frequency = logbookTaskModel.frequency
        )

        logbookApi.editTask(logbookRequest).collect {
            //_addTaskResult.value = it
        }
    }
}