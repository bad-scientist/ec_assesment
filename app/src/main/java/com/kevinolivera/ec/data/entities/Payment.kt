package com.kevinolivera.ec.data.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Payment(
    var date: String = "",
    var cardNumber: String = "",
    var expirationDate: String = "",
    var cvvCode: String = "",
    var total: Double,
    var tax: Double,
    var cartItems: MutableList<CartItem> = arrayListOf())
