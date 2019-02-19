package com.kevinolivera.ec.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.data.remote.api.ProductApi
import com.kevinolivera.ec.view.ProductView
import com.kevinolivera.ec.view.ProductsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsPresenter @Inject constructor(val productRepository: ProductRepository) {

    lateinit var productsView: ProductsView

    fun getProducts() {
        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("ProductPresenter1", Gson().toJson(it))
                if (it.statusCode == 200) {
                    productsView.onProducts(it.body)
                } else {
                    productsView.onError(it.message)
                }
            }, {
                productsView.onError(it.message!!)
            })
    }

}