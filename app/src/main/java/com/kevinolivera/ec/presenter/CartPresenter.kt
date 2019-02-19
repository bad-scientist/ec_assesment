package com.kevinolivera.ec.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.local.ProductDao
import com.kevinolivera.ec.data.remote.CartRepository
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.data.remote.api.ProductApi
import com.kevinolivera.ec.view.CartView
import com.kevinolivera.ec.view.ProductView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CartPresenter @Inject constructor(val productRepository: ProductRepository,
                                        val cartRepository: CartRepository) {

    private lateinit var cartView: CartView

    private var cartItems: MutableList<CartItem> = arrayListOf()
    private var products: MutableList<Product> = arrayListOf()

    private var count: Int = 0
    private var total: Double = 0.0

    fun initialize (cartView: CartView) {
        this.cartView = cartView
    }

    fun getCartItems () {
        cartRepository.getCartItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cartItems = it
                getProductsOnLocal()
            }, {
                cartView.onError(it.message!!)
            })
    }

    fun getProductsOnLocal () {
        productRepository.getLocalProducts()
        .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                products = it
                setupCartItems()
            }, {
                cartView.onError(it.message!!)
            })
    }

    fun setupCartItems () {
        for (cartItem: CartItem in cartItems) {
            cartItem.product = getProduct(cartItem.productId) ?: Product()
        }

        cartView.onCartItems(cartItems)
    }

    fun getProduct(id: Int) : Product? {
        for (product: Product in products) {
            if (product.id == id) {
                return product
            }
        }
        return null
    }


}