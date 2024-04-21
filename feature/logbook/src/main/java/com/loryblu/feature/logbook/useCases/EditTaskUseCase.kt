package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow

interface EditTaskUseCase {
    suspend fun invoke(logbookTask: LogbookTask): Flow<ApiResponse>
}