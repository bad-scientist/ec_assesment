package com.kevinolivera.ec.data.remote

import android.util.Log
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.entities.Request
import com.kevinolivera.ec.data.local.CartDao
import com.kevinolivera.ec.data.local.ProductDao
import com.kevinolivera.ec.data.remote.api.ProductApi
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi,
                                            var productDao: ProductDao) {
    val TAG = "ProductRepository"

    fun getProducts () : Observable<Request<MutableList<Product>>> =
        productApi.getProducts()
            .doOnNext { Log.d(TAG, it.message) }
            .doOnError { Log.d(TAG, it.message) }

    fun getProduct (id: Int) : Observable<Request<Product>> =
        productApi.getProduct(id)
            .doOnNext { Log.d(TAG, it.message) }
            .doOnError { Log.d(TAG, it.message) }

    fun saveProductToLocal (product: Product) : Observable<Unit>  {
        return Observable.fromCallable(object : Callable<Unit> {
            @Throws(Exception::class)
            override fun call() {
                productDao.update(product)
            }
        })
    }

    fun getLocalProducts () : Observable<MutableList<Product>> {
        return Observable.fromCallable(object : Callable<MutableList<Product>> {
            override fun call(): MutableList<Product>{
                return productDao.getProucts()
            }
        })
    }
}