package org.plux.marvelpedia.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val message: Throwable, val data: T? = null) : ApiResponse<T>()
    data class Loading<T>(val data: T? = null) : ApiResponse<T>()
}
