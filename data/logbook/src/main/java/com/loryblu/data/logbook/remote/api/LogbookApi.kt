package com.loryblu.data.logbook.remote.api

import com.loryblu.data.logbook.remote.model.LogbookTaskRequest

interface LogbookApi {
    suspend fun createTask(logbookTaskRequest: LogbookTaskRequest)
}