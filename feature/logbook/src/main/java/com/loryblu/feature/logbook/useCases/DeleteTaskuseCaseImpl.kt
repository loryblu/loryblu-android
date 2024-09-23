package com.loryblu.feature.logbook.useCases

import android.util.Log
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.feature.logbook.ui.task.delete.DeleteOption
import com.loryblu.feature.logbook.utils.intToShiftString
import kotlinx.coroutines.flow.Flow

internal class DeleteTaskUseCaseImpl(
    private val logbookRepository: LogbookApi
): DeleteTaskUseCase {
    override suspend fun invoke(
        logbookTask: LogbookTask,
        deleteOption: DeleteOption,
        childrenId: Int
    ): Flow<ApiResponse> {
        return when (deleteOption) {
            DeleteOption.Everyday ->
                logbookRepository.deleteTask(taskId =  logbookTask.id)

            is DeleteOption.SingleDay -> {

                if (logbookTask.frequency.size == 1) {
                    return logbookRepository.deleteTask(taskId =  logbookTask.id)
                }

                val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")
                val dayToBeDeleted = nameOfWeekDays[deleteOption.day]

                val updatedFrequency = logbookTask.frequency.toMutableList()
                updatedFrequency.remove(dayToBeDeleted)

                Log.d("current frequency", logbookTask.frequency.toString())
                Log.d("frequency day to be deleted", dayToBeDeleted)
                Log.d("updated frequency", updatedFrequency.toString())

                val taskRequest = logbookTask.run {
                    LogbookTaskRequest(
                        childrenId = childrenId,
                        categoryId = itemOfCategory.taskId,
                        shift = intToShiftString(shift.idCard),
                        frequency = updatedFrequency,
                        order = order
                    )
                }

                return logbookRepository.editTask(
                    logbookTaskRequest = taskRequest,
                    taskId = logbookTask.id,
                    childrenId = childrenId,
                )
            }
        }
    }
}