package com.kevinolivera.ec.data.remote

import android.util.Log
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.entities.Request
import com.kevinolivera.ec.data.remote.api.ProductApi
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi) {
    val TAG = "ProductRepository"

    fun getProducts () : Observable<Request<MutableList<Product>>> =
        productApi.getProducts()
            .doOnNext { Log.d(TAG, it.message) }
            .doOnError { Log.d(TAG, it.message) }

    fun getProduct (id: Int) : Observable<Request<Product>> =
        productApi.getProduct(id)
            .doOnNext { Log.d(TAG, it.message) }
            .doOnError { Log.d(TAG, it.message) }
}