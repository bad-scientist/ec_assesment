package com.kevinolivera.ec.data.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0
)
