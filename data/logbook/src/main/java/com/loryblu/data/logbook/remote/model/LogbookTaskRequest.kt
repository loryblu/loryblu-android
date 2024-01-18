package com.loryblu.data.logbook.remote.model

data class LogbookTaskRequest(
    val childrenId: Int,
    val categoryId: String,
    val shift: String,
    val frequency: List<String>,
    val order: Int = 0
)