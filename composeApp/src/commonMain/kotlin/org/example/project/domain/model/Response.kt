package org.example.project.domain.model

sealed class  Response<T> {
    class Loading<T> : Response<T>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val message: String? = null) : Response<T>()
}