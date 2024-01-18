package com.loryblu.data.logbook.remote.api

import com.loryblu.core.network.HttpRoutes
import com.loryblu.core.network.di.Session
import com.loryblu.data.logbook.remote.model.LogbookTaskRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

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
}