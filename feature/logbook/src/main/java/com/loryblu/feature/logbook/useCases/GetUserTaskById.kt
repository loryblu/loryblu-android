package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow

interface GetUserTaskById {
    suspend fun invoke(taskId: Int): Flow<ApiResponseWithData<LogbookTask?>>
}