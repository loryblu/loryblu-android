package com.loryblu.data.logbook.remote.model

import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem

data class LogbookTask(
    val itemOfCategory: TaskItem,
    val frequency: List<String>,
    val id: Int,
    val order: Int,
    val shift: ShiftItem,
    val updatedAt: String
)