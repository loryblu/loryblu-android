package com.loryblu.data.logbook.remote.api

import com.loryblu.core.network.HttpRoutes
import com.loryblu.core.network.di.Session
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.data.logbook.util.toListOfLogbookTask
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LogbookApiImpl(
    private val client: HttpClient,
    private val session: Session
): LogbookApi {
    override suspend fun createTask(logbookTaskRequest: LogbookTaskRequest) {
        try {
            client.post(HttpRoutes.TASK) {
                setBody(logbookTaskRequest)
                contentType(ContentType.Application.Json)
                bearerAuth(session.getToken())
            }
            return
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUserTasks(): Flow<ApiResponseWithData<List<LogbookTask>>> = flow {
        val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")

        emit(ApiResponseWithData.Loading())
        try{
            emit(
                client.get(HttpRoutes.TASK) {
                    parameters {
                        append("childrenId", session.getChildId().toString())
                        nameOfWeekDays.forEach {
                            append("frequency", it)
                        }
                    }
                    contentType(ContentType.Application.Json)
                    bearerAuth(session.getToken())
                }.toListOfLogbookTask()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponseWithData.Error())
        }
    }

}