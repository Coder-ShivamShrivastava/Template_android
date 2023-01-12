package com.template.network

import com.template.HomeData
import retrofit2.Response

interface RetrofitApi{


   suspend fun callServerApi():Response<HomeData>
   suspend fun callServer():Response<Any>
}
