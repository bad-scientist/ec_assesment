package com.kevinolivera.ec.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.google.gson.Gson
import com.kevinolivera.ec.App
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.presenter.ProductPresenter
import com.kevinolivera.ec.presenter.ProductsPresenter
import com.kevinolivera.ec.view.ProductView
import javax.inject.Inject
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kevinolivera.ec.R

class ProductActivity : AppCompatActivity(), ProductView {

    @BindView(R.id.tvName)
    lateinit var tvName: TextView

    @BindView(R.id.tvDescription)
    lateinit var tvDescription: TextView

    @BindView(R.id.tvPrice)
    lateinit var tvPrice: TextView

    @BindView(R.id.tvCount)
    lateinit var tvCount: TextView

    @BindView(R.id.btnAdd)
    lateinit var btnAdd: TextView

    @BindView(R.id.btnSubstract)
    lateinit var btnSubstract: TextView

    @BindView(R.id.tvTotalPrice)
    lateinit var tvTotalPrice: TextView

    @BindView(R.id.btnUpdateCart)
    lateinit var btnUpdateCart: TextView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var productPresenter: ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        ButterKnife.bind(this)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        (application as App).injector.inject(this)

        initialize()
    }

    fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)

        productPresenter.initialize(this)
        productPresenter.getProduct(intent.getIntExtra(PRODUCT_ID, 0))

        btnAdd.setOnClickListener { productPresenter.incrementCount() }
        btnSubstract.setOnClickListener { productPresenter.decrementCount() }
        btnUpdateCart.setOnClickListener {productPresenter.updateCart()}
        btnUpdateCart.visibility = View.GONE
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

    override fun onProduct(product: Product) {
        tvName.text = product.name
        tvDescription.text = product.description
        tvPrice.text = "₱ ${product.price}"
    }

    override fun onError(message: String) {
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        tvCount.text = "0"
        tvTotalPrice.text = "₱ 0.0"
    }

    override fun onCartItem(cartItem: CartItem) {
        btnUpdateCart.setText(if (cartItem.productId == 0) R.string.add_to_cart else R.string.udapte_product_on_cart)
        btnUpdateCart.visibility = View.VISIBLE
        tvCount.text = "${cartItem.count}"
        tvTotalPrice.text = "₱ ${cartItem.count * cartItem.product.price}"
    }

    override fun onCartItemSaved() {
        android.app.AlertDialog.Builder(this)
            .setTitle(R.string.cart_updated)
            .setMessage(R.string.udapte_product_on_cart)
            .setCancelable(false)
            .setPositiveButton(R.string.view_cart) { dialog, _ ->
               run {
                   startActivity(Intent(this@ProductActivity, CartActivity::class.java))
                   finish()
                    dialog.dismiss()
                }
            }
            .setNegativeButton(R.string.continue_shopping) { dialog, _ ->
                run {
                    dialog.dismiss()
                    finish()
                }
            }
            .show()
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
    }
}
