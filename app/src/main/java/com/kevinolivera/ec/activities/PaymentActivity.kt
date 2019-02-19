package com.kevinolivera.ec.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textfield.TextInputLayout
import com.kevinolivera.ec.App
import com.kevinolivera.ec.fragments.DatePickerFragment
import com.kevinolivera.ec.R
import com.kevinolivera.ec.fragments.SuccessDialogFragment
import com.kevinolivera.ec.presenter.PaymentPresenter
import com.kevinolivera.ec.view.PaymentView
import javax.inject.Inject

class PaymentActivity : AppCompatActivity(), PaymentView {


    @BindView(R.id.tvDate)
    lateinit var tvDate: TextView

    @BindView(R.id.tvSubTotal)
    lateinit var tvSubTotal: TextView

    @BindView(R.id.tvTax)
    lateinit var tvTax: TextView

    @BindView(R.id.tvTotal)
    lateinit var tvTotal: TextView

    @BindView(R.id.tilCardNumber)
    lateinit var tilCardNumber: TextInputLayout

    @BindView(R.id.tilExpirationDate)
    lateinit var tilExpirationDate: TextInputLayout

    @BindView(R.id.tilCVVCode)
    lateinit var tilCVVCode: TextInputLayout

    @BindView(R.id.btnPayCart)
    lateinit var btnPay: Button

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var paymentPresenter: PaymentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)
        ButterKnife.bind(this)
        (application as App).injector.inject(this)

        initialize()
    }

    fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.activity_payment_header)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)

        paymentPresenter.initialize(this)
        paymentPresenter.computeTotals(intent.getDoubleExtra(SUB_TOTAL, 0.0))

        tilExpirationDate.editText?.setOnClickListener {
            val fragment = DatePickerFragment()
            fragment.onDateSelected = object : DatePickerFragment.OnDateSelected {
                override fun onDateSelected(year: Int, month: Int) {
                    tilExpirationDate.editText?.setText("${if (month + 1 < 10) "0${month+1}" else month+1}/$year")
                }
            }
            fragment.show(supportFragmentManager, "Select Expiration Date")
        }

        btnPay.setOnClickListener { paymentPresenter.payCart(
            cardNumber = tilCardNumber.editText?.text.toString(),
            cvvNumber = tilCVVCode.editText?.text.toString(),
            expDate = tilExpirationDate.editText?.text.toString()) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return true
    }

    override fun onPaymentDetails(date: String, subTotal: String, tax: String, total: String) {
        tvDate.text = date
        tvTax.text = tax
        tvTotal.text = total
        tvSubTotal.text = subTotal
    }

    override fun onPaymentSuccess() {
        SuccessDialogFragment.newInstance(tvTotal.text.toString()).show(supportFragmentManager, "")
    }

    override fun onPaymentError() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String) {
        //TODO
    }

    companion object {
        const val SUB_TOTAL = "SUB_TOTAL"

        fun startActivity (activity:Activity, subTotal: Double) {
            var intent = Intent(activity, PaymentActivity::class.java)
            intent.putExtra(SUB_TOTAL, subTotal)
            activity.startActivity(intent)
        }
    }


}
