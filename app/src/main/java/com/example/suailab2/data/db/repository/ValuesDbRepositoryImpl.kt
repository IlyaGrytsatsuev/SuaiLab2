package com.example.suailab2.data.db.repository

import com.example.suailab2.data.converters.toDoublesList
import com.example.suailab2.data.converters.toValuesEntity
import com.example.suailab2.data.db.dao.ValuesDao
import com.example.suailab2.domain.repository.ValuesDbRepository
import javax.inject.Inject

class ValuesDbRepositoryImpl @Inject constructor
    (private val dao: ValuesDao): ValuesDbRepository {
    override suspend fun upsertValue(value: Double) {
        dao.upsertNewValue(value.toValuesEntity())
        deleteOldValues()
    }

    override suspend fun getLastValues(): List<Double> =
        dao.getLastValues().toDoublesList()


    override suspend fun deleteOldValues() {
        dao.deleteOldValue()
    }
}