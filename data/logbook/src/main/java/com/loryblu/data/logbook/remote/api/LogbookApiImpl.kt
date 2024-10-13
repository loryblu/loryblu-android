package com.loryblu.data.logbook.remote.api

import com.loryblu.core.network.HttpRoutes
import com.loryblu.core.network.di.UserSession
import com.loryblu.core.network.extensions.toApiResponse
import com.loryblu.core.network.model.ApiResponse
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import com.loryblu.data.logbook.util.toListOfLogbookTask
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LogbookApiImpl(
    private val client: HttpClient,
    private val userSession: UserSession
) : LogbookApi {

    override suspend fun createTask(logbookTaskRequest: LogbookTaskRequest) = flow {
        emit(ApiResponse.Loading)
        try {
            emit(
                client.post(HttpRoutes.TASK) {
                    setBody(logbookTaskRequest)
                    contentType(ContentType.Application.Json)
                    bearerAuth(userSession.getToken())
                }.toApiResponse()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.ErrorDefault)
        }
    }

    override suspend fun editTask(
        logbookTaskRequest: LogbookTaskRequest,
        taskId: Int
    ) = flow {
        emit(ApiResponse.Loading)
        try {
            emit(
                client.patch(HttpRoutes.TASK) {
                    parameter("id_task", taskId)
                    setBody(logbookTaskRequest)
                    contentType(ContentType.Application.Json)
                    bearerAuth(userSession.getToken())
                }.toApiResponse()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.ErrorDefault)
        }
    }

    override fun deleteTask(
        taskId: Int
    ): Flow<ApiResponse> = flow {
        emit(ApiResponse.Loading)
        try {
            emit(
                client.delete(HttpRoutes.TASK) {
                    parameter("taskId", taskId)
                    parameter("childrenId", userSession.getChildId().toString())
                    bearerAuth(userSession.getToken())
                }.toApiResponse()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponse.ErrorDefault)
        }
    }

    override suspend fun getUserTasks(): Flow<ApiResponseWithData<List<LogbookTask>>> = flow {
        val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")

        emit(ApiResponseWithData.Loading())
        try {
            emit(
                client.get(HttpRoutes.TASK) {
                    parameters {
                        parameter("childrenId", userSession.getChildId().toString())
                        nameOfWeekDays.forEach {
                            parameter("frequency", it)
                        }
                        parameter("perPage", 70)
                    }
                    contentType(ContentType.Application.Json)
                    bearerAuth(userSession.getToken())
                }.toListOfLogbookTask()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResponseWithData.Error())
        }
    }

}