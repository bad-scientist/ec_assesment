package com.kevinolivera.ec.data.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Product(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0
)
