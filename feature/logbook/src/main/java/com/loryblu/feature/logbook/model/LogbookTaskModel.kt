package com.loryblu.feature.logbook.model

import com.loryblu.data.logbook.local.CategoryItem

data class LogbookTaskModel(
    var category: CategoryItem,
    var task: String,
    var shift: String,
    var frequency: List<String>,
    var taskId: Int = 0,
    var taskOrder: Int = 0,
)
