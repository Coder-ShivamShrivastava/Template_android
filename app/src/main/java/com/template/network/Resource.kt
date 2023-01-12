package com.template.network


sealed class NetworkResult<out T>() {
    class Success< T>(data: T) : NetworkResult<T>()
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>()
    object Loading : NetworkResult<Nothing>()
}
