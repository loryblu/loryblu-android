package com.loryblu.data.logbook.remote.model

data class LogbookTask(
    val categoryId: String,
    val categoryTitle: String,
    val frequency: List<String>,
    val id: Int,
    val order: Int,
    val shift: String,
    val updatedAt: String
)