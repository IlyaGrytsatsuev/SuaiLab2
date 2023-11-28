package com.example.suailab2.data.network.repository

import android.util.Log
import com.example.suailab2.data.converters.toRequestBody
import com.example.suailab2.data.network.service.ValuesApiService
import com.example.suailab2.domain.repository.ValuesApiRepository
import com.example.suailab2.domain.repository.ValuesDbRepository
import javax.inject.Inject

class ValuesApiRepositoryImpl @Inject constructor
    (private val apiService: ValuesApiService,
     private val dbRepository: ValuesDbRepository):ValuesApiRepository {
    override suspend fun sendValue(value: Double, url:String?) {
        if(url.isNullOrBlank())
            apiService.sendValue(
                body = value.toRequestBody())
        else
            apiService.sendValueOnCustomUrl(url = url,
                body = value.toRequestBody())

        dbRepository.upsertValue(value)

    }

}