package com.kevinolivera.ec.data.entities

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var count: Int = 0,
    var productId: Int = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0) {

    fun setProduct (product: Product) {
        name = product.name
        description = product.description
        price = product.price
    }
}