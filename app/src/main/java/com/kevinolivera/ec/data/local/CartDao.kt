package com.kevinolivera.ec.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.kevinolivera.ec.data.entities.CartItem

@Dao
interface CartDao : BaseDao<CartItem> {

    @Query("SELECT * FROM cartitem WHERE productId = :id")
    fun getCartItem(id: Int): CartItem

    @Query("SELECT * FROM cartitem")
    fun getCartItems(): MutableList<CartItem>

    @Transaction
    fun updateCartItem(cartItem: CartItem) {
        deleteCartItem(cartItem.productId)
        insert(cartItem)
    }

    @Query("DELETE FROM cartitem WHERE productId = :id")
    fun deleteCartItem(id: Int)
}
