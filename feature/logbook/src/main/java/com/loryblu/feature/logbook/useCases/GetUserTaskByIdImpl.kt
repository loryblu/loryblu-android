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
        logbookRepository.getUserTasks().collect {
            if(it::class == ApiResponseWithData.Success::class) {
                it.data?.apply {
                    updateUserTasks(newTaskList = this)
                }
                emitResult(taskId)
            } else {
                emit(ApiResponseWithData.Error())
            }
        }
    }

    private fun updateUserTasks(newTaskList: List<LogbookTask>) {
        userTasks.clear()
        userTasks.addAll(newTaskList)
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
