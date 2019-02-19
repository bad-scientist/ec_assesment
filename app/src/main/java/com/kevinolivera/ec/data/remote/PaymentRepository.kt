package com.kevinolivera.ec.data.remote

import android.util.Log
import com.kevinolivera.ec.data.entities.Payment
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.entities.Request
import com.kevinolivera.ec.data.remote.api.PaymentApi
import com.kevinolivera.ec.data.remote.api.ProductApi
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject

class PaymentRepository @Inject constructor(private val paymentApi: PaymentApi) {
    val TAG = "ProductRepository"

    fun payCart (payment: Payment) : Observable<Request<Payment>> =
        paymentApi.paycart(payment)
            .doOnNext { Log.d(TAG, it.message) }
            .doOnError { Log.d(TAG, it.message) }
}