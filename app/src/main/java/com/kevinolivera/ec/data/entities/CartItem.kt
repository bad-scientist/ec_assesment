package com.kevinolivera.ec.data.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class CartItem(
    @PrimaryKey
    var id: Int = 0,
    var count: Int = 0,
    var productId: Int = 0,
    @Ignore
    var product: Product = Product())