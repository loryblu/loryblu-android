package com.loryblu.feature.logbook.useCases

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.api.LogbookApi
import com.loryblu.data.logbook.remote.model.LogbookTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class GetUserTaskByIdImpl(
    private val logbookRepository: LogbookApi
): GetUserTaskById {

    private val userTasks: MutableList<LogbookTask> = mutableListOf()

    override suspend fun invoke(taskId: Int): Flow<ApiResponseWithData<LogbookTask?>> = flow {
        if(userTasks.isNotEmpty()) {
            emitResult(taskId)
        } else {
            logbookRepository.getUserTasks().collect {
                if(it::class == ApiResponseWithData.Success::class) {
                    it.data?.let { d -> userTasks.addAll(d) }
                    emitResult(taskId)
                } else {
                    emit(ApiResponseWithData.Error())
                }
            }
        }
    }

    private suspend fun FlowCollector<ApiResponseWithData<LogbookTask?>>.emitResult(taskId: Int) {
        val userTask = userTasks.find { t -> t.id == taskId }
        if (userTask != null) {
            emit(ApiResponseWithData.Success(userTask))
        } else {
            emit(ApiResponseWithData.Error())
        }
    }
}