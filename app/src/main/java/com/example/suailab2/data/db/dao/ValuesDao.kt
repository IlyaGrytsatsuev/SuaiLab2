package com.example.suailab2.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.suailab2.data.db.ValuesEntity
import com.example.suailab2.utils.Constants

@Dao
interface ValuesDao {

    @Upsert
    suspend fun upsertNewValue(value:ValuesEntity)

    @Query("SELECT * FROM ValuesEntity LIMIT :limit")
    suspend fun getLastValues(limit:Int = Constants.VALUES_STORED_NUM):List<ValuesEntity>

    @Query("DELETE FROM ValuesEntity WHERE id NOT IN (SELECT id FROM ValuesEntity" +
            " ORDER BY id DESC LIMIT :limit)")
    suspend fun deleteOldValue(limit: Int = Constants.VALUES_STORED_NUM)
}