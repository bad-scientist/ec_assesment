package com.kevinolivera.ec.data.remote.api

import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.entities.Request
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/product")
    fun getProducts() : Observable<Request<MutableList<Product>>>

    @GET("/product/{product_id}")
    fun getProduct(@Path("product_id") productId: Int) : Observable<Request<Product>>

}