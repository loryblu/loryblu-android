package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GetUserTaskByDayOfWeekImpl(
    private val logbookRepository: LogbookApi
): GetUserTaskByDayOfWeek {
    private val userTasks: MutableList<LogbookTask> = mutableListOf()

    override suspend fun invoke(dayOfWeek: String): Flow<ApiResponseWithData<List<LogbookTask>>> = flow {
        if(userTasks.isNotEmpty()) {
            emit(
                ApiResponseWithData.Success(
                    getTasksByDayOfWeek(userTasks, dayOfWeek)
                )
            )
        } else {
            logbookRepository.getUserTasks().collect {
                emit(it)
                if(it == ApiResponseWithData.Success::class) {
                    userTasks.addAll(it.data!!)
                }
            }
        }
    }

    private fun getTasksByDayOfWeek(list: List<LogbookTask>, dayOfWeek: String): List<LogbookTask> {
        val result = mutableListOf<LogbookTask>()
        list.forEach {
            if(it.frequency.contains(dayOfWeek)) {
                result.add(it)
            }
        }
        return result
    }

}