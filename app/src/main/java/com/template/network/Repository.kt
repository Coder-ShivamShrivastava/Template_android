package com.template.network

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    @ApplicationContext private val context: Context
) {
    suspend fun <T> makeApiCall(apiCall: suspend () -> Response<T>): Flow<NetworkProcess<T>> =
        flow {
            try {
                val response = apiCall()
                emit(NetworkProcess.Loading)
                when {
                    response.code() in 100..199 -> {}
                    response.isSuccessful -> {
                        val body = response.body()
                        body?.let {
                            emit(NetworkProcess.Success(body))
                        }
                    }
                    response.code() in 300..399 -> {
                    }
                    response.code() == 401 -> {
                    }
                    response.code() == 404 -> {

                    }
                    response.code() in 500..599 -> {
                    }
                    else -> {
                        emit(NetworkProcess.Error(response.message(), null))
                    }
                }

            } catch (e: Exception) {
                emit(NetworkProcess.Error(e.message.toString(), null))
            }
        }.flowOn(Dispatchers.IO)


}
