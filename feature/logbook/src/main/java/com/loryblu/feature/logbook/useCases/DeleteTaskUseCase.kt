package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.ui.task.delete.DeleteOption
import kotlinx.coroutines.flow.Flow

interface DeleteTaskUseCase {

    suspend fun invoke(
        logbookTask: LogbookTask,
        deleteOption: DeleteOption,
        childrenId: Int
    ): Flow<ApiResponse>
}