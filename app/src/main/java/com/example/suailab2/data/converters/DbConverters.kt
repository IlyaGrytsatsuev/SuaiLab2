package com.example.suailab2.data.converters

import com.example.suailab2.data.db.ValuesEntity



fun Double.toValuesEntity() : ValuesEntity = ValuesEntity(value = this)

fun ValuesEntity.toDouble() = this.value

fun List<ValuesEntity>.toDoublesList() : List<Double>{
    val resList :MutableList<Double> = mutableListOf()
    this.forEach { resList.add(it.toDouble()) }
    return resList
}
