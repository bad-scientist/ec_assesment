package com.kevinolivera.ec.data.entities

import com.google.gson.annotations.SerializedName

data class Request<T>(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("body")
    val body: T
)