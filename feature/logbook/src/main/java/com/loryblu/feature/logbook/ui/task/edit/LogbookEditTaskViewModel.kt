package com.loryblu.feature.logbook.ui.task.edit

import androidx.lifecycle.ViewModel
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.logbook.model.LogbookTaskModel

class LogbookEditTaskViewModel(
    private val logbookTaskModel: LogbookTaskModel,
) : ViewModel() {

    fun getLogbookTaskModel() : LogbookTaskModel = LogbookTaskModel(
        category = CategoryItem.Routine,
        task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
        shift = "morning",
        frequency = listOf("sun", "mon")
    )
}