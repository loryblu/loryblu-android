package com.loryblu.feature.logbook.useCases

import android.util.Log
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
        Log.d("Testing", "Call on invoke GetUserTaskByDayOfWeekImpl: $dayOfWeek - $shift - $force")
        if(userTasks.isNotEmpty() && !force) {
            Log.d("Testing", "GetUserTaskByDayOfWeekImpl: userTasks is not empty")
            emit(
                ApiResponseWithData.Success(
                    getTasksByDayOfWeekAndShift(userTasks, dayOfWeek, shift)
                )
            )
        } else {
            Log.d("Testing", "GetUserTaskByDayOfWeekImpl: userTasks is empty")

            logbookRepository.getUserTasks().collect {
                if(it::class == ApiResponseWithData.Success::class) {
                    userTasks.addAll(it.data!!)
                    Log.d("Testing", "GetUserTaskByDayOfWeekImpl: getUserTasks emit userTasks")

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