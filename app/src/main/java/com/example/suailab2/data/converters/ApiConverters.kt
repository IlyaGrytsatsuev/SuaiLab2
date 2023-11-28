package com.example.suailab2.data.converters

import com.example.suailab2.data.network.requestModels.ValueRequestBody

fun Double.toRequestBody(): ValueRequestBody = ValueRequestBody(this)
