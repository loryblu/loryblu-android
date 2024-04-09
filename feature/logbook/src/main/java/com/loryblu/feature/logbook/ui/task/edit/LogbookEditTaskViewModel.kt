package com.loryblu.feature.logbook.ui.task.edit

import androidx.lifecycle.ViewModel
import com.loryblu.feature.logbook.model.LogbookTaskModel

class LogbookEditTaskViewModel(
    private val logbookTaskModel: LogbookTaskModel,
) : ViewModel() {

    fun getLogbookTaskModel() : LogbookTaskModel = logbookTaskModel

}