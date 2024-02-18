package com.loryblu.data.logbook.util

import com.loryblu.core.network.model.ApiResponseWithData
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
                remote.data.routine.map {
                    LogbookTask(
                        categoryId = it.categoryId,
                        categoryTitle = it.categoryTitle,
                        frequency = it.frequency,
                        id = it.id,
                        order = it.order,
                        shift = it.shift,
                        updatedAt = it.updatedAt
                    )
                }
            )
        }

        else -> {
            ApiResponseWithData.DefaultError()
        }
    }
}