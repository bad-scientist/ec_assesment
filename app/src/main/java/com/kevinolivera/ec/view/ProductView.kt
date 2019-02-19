package com.kevinolivera.ec.view

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

interface ProductView {
    fun onProduct(product: Product)
    fun onCartItem(cartITem: CartItem)
    fun onCartItemSaved()
    fun onError(message: String)
}