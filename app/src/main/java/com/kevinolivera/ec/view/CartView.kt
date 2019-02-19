package com.kevinolivera.ec.view

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

interface CartView {
    fun onCartItems(cartItem: MutableList<CartItem>)
    fun onPayment()
    fun onError(message: String)
}