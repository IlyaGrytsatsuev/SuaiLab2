package com.example.suailab2.data.network.service

import com.example.suailab2.data.network.requestModels.ValueRequestBody
import com.example.suailab2.utils.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ValuesApiService {

    @POST("{id}")
    suspend fun sendValue(@Path("id")id:Int = Constants.REQUEST_GROUP_ID,
                          @Body body:ValueRequestBody): ResponseBody

    @POST
    suspend fun sendValueOnCustomUrl(@Url url:String,
                          @Body body:ValueRequestBody): ResponseBody

}