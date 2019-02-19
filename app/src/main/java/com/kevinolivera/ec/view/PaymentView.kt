package com.kevinolivera.ec.view

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product

interface PaymentView {
    fun onPaymentDetails(date: String, subTotal: String, tax:String, total:String)
    fun onPaymentSuccess()
    fun onPaymentError()
    fun onError(message: String)
}