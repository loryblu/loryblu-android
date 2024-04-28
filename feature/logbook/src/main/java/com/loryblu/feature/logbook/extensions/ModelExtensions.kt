package com.loryblu.feature.logbook.extensions

import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.model.LogbookTaskModel

fun LogbookTaskModel.toLogbookTask(): LogbookTask {
    return LogbookTask(
        id = taskId,
        order = taskOrder,
        itemOfCategory = TaskItem.getTaskItem(task),
        frequency = frequency,
        shift = ShiftItem.getShiftItem(shift),
        updatedAt = ""
    )
}
