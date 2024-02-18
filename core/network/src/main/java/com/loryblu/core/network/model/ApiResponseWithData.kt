package com.loryblu.core.network.model

sealed class ApiResponseWithData<T>(val data: T? = null){
    class Success<T>(result: T) : ApiResponseWithData<T>(data = result)
    class Error<T> : ApiResponseWithData<T>()
    class DefaultError<T> : ApiResponseWithData<T>()
    class Loading<T> : ApiResponseWithData<T>()
    class EmptyData<T> : ApiResponseWithData<T>()
    class Default<T> : ApiResponseWithData<T>()
}
