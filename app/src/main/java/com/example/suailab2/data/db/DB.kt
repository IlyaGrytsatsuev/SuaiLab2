package com.example.suailab2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.suailab2.data.db.dao.ValuesDao

@Database(entities = [ValuesEntity::class], version = 1 )
abstract class DB : RoomDatabase() {
    abstract fun  valuesDao(): ValuesDao
}