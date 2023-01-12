package com.template.network


sealed class NetworkProcess<out T>() {
    class Success< T>(data: T) : NetworkProcess<T>()
    class Error<T>(message: String, data: T? = null) : NetworkProcess<T>()
    object Loading : NetworkProcess<Nothing>()
}
