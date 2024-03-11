package com.loryblu.data.logbook.util

import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.data.logbook.remote.model.LogbookTaskRemote
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend fun HttpResponse.toListOfLogbookTask(): ApiResponseWithData<List<LogbookTask>> {
    return when (this.status) {
        HttpStatusCode.OK -> {
            val remote = this.body<LogbookTaskRemote?>() ?: return ApiResponseWithData.EmptyData()
            return ApiResponseWithData.Success(
                remote.toLogbookTask()
            )
        }

        else -> {
            ApiResponseWithData.DefaultError()
        }
    }
}

fun LogbookTaskRemote.toLogbookTask(): List<LogbookTask> {
    val idToCard = TaskItem.getAllTaskItems().associateBy { it.taskId }

    return this.data.routine.map { remote ->
        LogbookTask(
            itemOfCategory = idToCard[remote.categoryId]!!,
            frequency = remote.frequency,
            id = remote.id,
            order = remote.order,
            shift = ShiftItem.getShiftItem(remote.shift),
            updatedAt = remote.updatedAt
        )
    }
}
