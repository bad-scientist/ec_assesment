package com.kevinolivera.ec.data.remote.api

import com.kevinolivera.ec.data.entities.Payment
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.entities.Request
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface PaymentApi {
    @PUT("/payment")
    fun paycart(@Body payment: Payment) : Observable<Request<Payment>>

}