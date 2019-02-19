package com.kevinolivera.ec.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

@Dao
interface ProductDao : BaseDao<Product> {

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProduct(id: Int): Product

    @Query("SELECT * FROM product")
    fun getProucts(): MutableList<Product>

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteProduct(id: Int)

    @Transaction
    fun saveProduct(product: Product) {
        deleteProduct(product.id)
        insert(product)
    }
}
