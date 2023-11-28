package com.example.suailab2.domain.repository

interface ValuesApiRepository {

    suspend fun sendValue(value: Double, url:String?)
}