package com.kevinolivera.ec.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.kevinolivera.ec.App
import com.kevinolivera.ec.R
import com.kevinolivera.ec.adapter.RecyclerAdapter
import com.kevinolivera.ec.adapter.holde.CartItemHolder
import com.kevinolivera.ec.data.entities.CartItem
import com.kevinolivera.ec.presenter.CartPresenter
import com.kevinolivera.ec.view.CartView
import javax.inject.Inject

class CartActivity : AppCompatActivity(), CartView {

    @BindView(R.id.rvCartItems)
    lateinit var rvCartItems: RecyclerView

    @BindView(R.id.tvSubTotal)
    lateinit var tvItems: TextView

    @BindView(R.id.tvTotal)
    lateinit var tvTotalPrice: TextView

    @BindView(R.id.btnPayCart)
    lateinit var btnPayCart: Button

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var cartPresenter: CartPresenter

    lateinit var cartAdapter: RecyclerAdapter<CartItemHolder, CartItem>

    var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        ButterKnife.bind(this)
        (application as App).injector.inject(this)
        initialize()
    }

    fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.activity_cart_header)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)

        rvCartItems.layoutManager = LinearLayoutManager(this)
        rvCartItems.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        cartAdapter = RecyclerAdapter(this, CartItemHolder.LAYOUT, CartItemHolder::class.java)
        rvCartItems.adapter = cartAdapter

        cartAdapter.setOnEventListener(object : RecyclerAdapter.OnEventListener<CartItem> {
            override fun onSelect(cartItem: CartItem) {
                var intent = Intent(this@CartActivity, ProductActivity::class.java)
                intent.putExtra(ProductActivity.PRODUCT_ID, cartItem.productId)
                startActivity(intent)
            }
        })

        cartPresenter.initialize(this)
        cartPresenter.getCartItems()

        btnPayCart.setOnClickListener { cartPresenter.payCart() }
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

    override fun onError(message: String) {
        //TODO
    }

    override fun onCartItems(cartItems: MutableList<CartItem>, count: Int, total: Double) {
        this.total = total
        cartAdapter.setList(cartItems)
        tvItems.text = "$count"
        tvTotalPrice.text = "₱ $total"
    }

    override fun onPayment() {
        PaymentActivity.startActivity(this, total)
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
    }


}
