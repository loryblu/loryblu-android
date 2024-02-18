package com.loryblu.data.logbook.remote.model

data class LogbookTaskRemote(
    val data: Data,
    val message: String
)

data class Data(
    val count: Int,
    val routine: List<Routine>
)

data class Routine(
    val categoryId: String,
    val categoryTitle: String,
    val frequency: List<String>,
    val id: Int,
    val order: Int,
    val shift: String,
    val updatedAt: String
)