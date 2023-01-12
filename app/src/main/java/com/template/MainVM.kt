package com.template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.network.Repository
import com.template.network.RetrofitApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainVM(val repository: Repository,private val retrofitApi: RetrofitApi):ViewModel() {


    init {
        callApi()
    }

    fun callApi(){
        viewModelScope.launch {
            repository.safeApiCall {
                retrofitApi.callServerApi()
            }.collect{

            }
        }

    }


}


data class HomeData(var str:String)