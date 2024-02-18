package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow

interface GetUserTaskByDayOfWeek {
    suspend fun invoke(dayOfWeek: String): Flow<ApiResponseWithData<List<LogbookTask>>>
}
