package com.template.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    private val retrofitApi: RetrofitApi
) {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<NetworkResult<T>> = flow {
       emit(NetworkResult.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {

                    emit(NetworkResult.Success(body))

                }
            }
             emit(NetworkResult.Error(response.message(),null))

        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message.toString(),null))
        }
    }.flowOn(Dispatchers.IO)












}
