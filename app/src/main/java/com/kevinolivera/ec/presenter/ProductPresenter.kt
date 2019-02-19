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
import com.kevinolivera.ec.view.ProductView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductPresenter @Inject constructor(val productRepository: ProductRepository,
                                           val cartRepository: CartRepository) {

    private lateinit var productView: ProductView

    lateinit var product:Product
    lateinit var cartItem : CartItem

    fun initialize (productView: ProductView) {
        this.productView = productView
        cartItem =  CartItem()
    }

    fun getCartItem(id: Int) {
        cartRepository.getCartItem(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cartItem = it
                cartItem.product = product
                productView.onCartItem(cartItem)
            }, {
                productView.onError(it.message!!)
            })
    }

    fun getProduct(id: Int) {
        productRepository.getProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("ProductPresenter1", Gson().toJson(it))
                if (it.statusCode == 200) {
                    product = it.body
                    cartItem.product = product
                    productView.onProduct(it.body)
                    getCartItem(id)
                } else {
                    productView.onError(it.message)
                }
            }, {
                productView.onError(it.message!!)
            })
    }

    fun incrementCount(){
        cartItem.count++
        productView.onCartItem(cartItem)
    }

    fun decrementCount(){
        if (cartItem.count > 0) {
            cartItem.count--
            productView.onCartItem(cartItem)
        }
    }

    fun saveProduct () {
        productRepository.saveProductToLocal(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveCart()
            }, {
                productView.onError(it.message!!)
            })
    }

    fun saveCart() {
        cartItem?.productId = product.id
        cartRepository.saveCartItem(cartItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productView.onCartItemSaved()
            }, {
                productView.onError(it.message!!)
            })
    }

    fun updateCart () {
        saveProduct()
    }


}