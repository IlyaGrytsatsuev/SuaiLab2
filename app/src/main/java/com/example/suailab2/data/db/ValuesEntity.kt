package com.example.suailab2.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ValuesEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    @ColumnInfo(name = "value")
    val value:Double = 0.0
)
