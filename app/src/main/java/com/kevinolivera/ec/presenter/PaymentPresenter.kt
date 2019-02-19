package com.kevinolivera.ec.presenter

import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Payment
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.data.remote.CartRepository
import com.kevinolivera.ec.data.remote.PaymentRepository
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.view.CartView
import com.kevinolivera.ec.view.PaymentView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PaymentPresenter @Inject constructor(val paymentRepository: PaymentRepository,
                                           val cartRepository: CartRepository) {

    private lateinit var paymentView: PaymentView

    private var cartItems: MutableList<CartItem> = arrayListOf()

    private var total: Double = 0.0
    private var subTotal: Double = 0.0
    private var tax = .12

    private var simpleDateTime = SimpleDateFormat("dd-MMM-yyyy")
    private var date = simpleDateTime.format(Date())

    fun initialize (paymentView: PaymentView) {
        this.paymentView = paymentView
        getCartItem()
        //SHOW LOADER
    }

    fun computeTotals (subTotal: Double) {
        this.subTotal = subTotal
        this.total = subTotal + (subTotal * tax)
        paymentView.onPaymentDetails(date, "₱ ${subTotal}", "${ tax * 100 } %", "₱ $total")
    }

    fun payCart (cardNumber: String, expDate:String, cvvNumber:String) {
        var payment = Payment(
            cardNumber = cardNumber,
            expirationDate = expDate,
            cvvCode = cvvNumber,
            cartItems = cartItems,
            date = date,
            tax = tax,
            total = total)

        paymentRepository.payCart(payment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.statusCode == 200) {
                    clearCart()
                } else {
                    paymentView.onPaymentError()
                }
            }, {
                paymentView.onPaymentError()
            })
    }

    fun clearCart() {
        cartRepository.clearCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                paymentView.onPaymentSuccess()
            }, {
                paymentView.onPaymentError()
            })
    }

    fun getCartItem () {
        cartRepository.getCartItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                cartItems = it
                //HIDE LOADER
            }, {
            })
    }


}