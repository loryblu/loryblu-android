package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.utils.intToShiftString
import kotlinx.coroutines.flow.Flow

internal class EditTaskUseCaseImpl(
    private val logbookRepository: LogbookApi
): EditTaskUseCase {

    override suspend fun invoke(logbookTask: LogbookTask): Flow<ApiResponse> {
        val taskId = logbookTask.id

        val taskRequest = logbookTask.run {
            LogbookTaskRequest(
                childrenId = 1,
                categoryId = itemOfCategory.category.idCard.toString(),
                shift = intToShiftString(shift.idCard),
                frequency = frequency,
                order = order
            )
        }

        return logbookRepository.editTask(
            logbookTaskRequest = taskRequest,
            taskId = taskId
        )
    }
}