package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GetUserTaskByDayOfWeekImpl(
    private val logbookRepository: LogbookApi
): GetUserTaskByDayOfWeek {
    private val userTasks: MutableList<LogbookTask> = mutableListOf()

    override suspend fun invoke(dayOfWeek: String, shift: Int, force: Boolean): Flow<ApiResponseWithData<List<LogbookTask>>> = flow {
        if(userTasks.isNotEmpty() && !force) {
            emit(
                ApiResponseWithData.Success(
                    getTasksByDayOfWeekAndShift(userTasks, dayOfWeek, shift)
                )
            )
        } else {
            logbookRepository.getUserTasks().collect {
                if(it::class == ApiResponseWithData.Success::class) {
                    it.data?.apply {
                        updateUserTasks(newTaskList = this)
                    }
                    emit(
                        ApiResponseWithData.Success(
                            getTasksByDayOfWeekAndShift(userTasks, dayOfWeek, shift)
                        )
                    )
                } else {
                    emit(it)
                }
            }
        }
    }

    private fun updateUserTasks(newTaskList: List<LogbookTask>) {
        userTasks.clear()
        userTasks.addAll(newTaskList)
    }

    private fun getTasksByDayOfWeekAndShift(list: List<LogbookTask>, dayOfWeek: String, shift: Int): List<LogbookTask> {
        val result = mutableListOf<LogbookTask>()
        val shiftItem = ShiftItem.getShiftItem(shift)

        list.forEach {
            if(it.frequency.contains(dayOfWeek) && it.shift == shiftItem) {
                result.add(it)
            }
        }
        return result
    }
}
