package com.kevinolivera.ec.presenter

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.remote.CartRepository
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.view.CartView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CartPresenter @Inject constructor(val cartRepository: CartRepository) {

    private lateinit var cartView: CartView

    private var cartItems: MutableList<CartItem> = arrayListOf()

    private var count: Int = 0
    private var total: Double = 0.0

    fun initialize (cartView: CartView) {
        this.cartView = cartView
    }

    fun getCartItems () {
        cartRepository.getCartItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                cartItems = it
                getTotalPriceAndCount()
            }, {
                cartView.onError(it.message!!)
            })
    }

    fun getTotalPriceAndCount () {
        for (cartItem : CartItem in cartItems) {
            count += cartItem.count;
            total += cartItem.count * cartItem.price
        }
        cartView.onCartItems(cartItems, count, total)
    }

    fun payCart() {
        if (count > 0) {
            cartView.onPayment()
        }
        //TODO Error
    }

}