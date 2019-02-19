package com.kevinolivera.ec.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

@Database(entities = [CartItem::class, Product::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCartDao(): CartDao
    abstract fun getProductDao(): ProductDao
}
