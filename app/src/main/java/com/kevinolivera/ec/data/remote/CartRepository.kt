package com.kevinolivera.ec.data.remote

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.local.CartDao
import com.kevinolivera.ec.data.remote.api.ProductApi
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject

class CartRepository @Inject constructor(var cartDao: CartDao) {

    val TAG = "CartRepository"

    fun getCartItems () : Observable<MutableList<CartItem>> {
        return Observable.fromCallable(object : Callable<MutableList<CartItem>> {
            override fun call(): MutableList<CartItem>{
                return cartDao.getCartItems()
            }
        })
    }

    fun getCartItem (productID:Int) : Observable<CartItem> {
        return Observable.fromCallable(object : Callable<CartItem> {
            override fun call(): CartItem {
                return cartDao.getCartItem(productID)
            }
        })
    }

    fun saveCartItem (cartItem: CartItem) : Observable<Unit>  {
        return Observable.fromCallable(object : Callable<Unit> {
            override fun call() {
                cartDao.updateCartItem(cartItem)
            }
        })
    }

    fun clearCart () : Observable<Unit>  {
        return Observable.fromCallable(object : Callable<Unit> {
            override fun call() {
                cartDao.clear()
            }
        })
    }
}