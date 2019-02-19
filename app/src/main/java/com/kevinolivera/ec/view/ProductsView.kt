package com.kevinolivera.ec.view

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

interface ProductsView {
    fun onProducts(products: MutableList<Product>)
    fun onError(message: String)
}