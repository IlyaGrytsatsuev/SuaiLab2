package com.example.suailab2.domain.repository

interface ValuesDbRepository {

    suspend fun upsertValue(value:Double)

    suspend fun getLastValues():List<Double>

    suspend fun deleteOldValues()
}