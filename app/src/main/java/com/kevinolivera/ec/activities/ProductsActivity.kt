package com.kevinolivera.ec.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
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
import com.kevinolivera.ec.adapter.holde.ProductHolder
import com.kevinolivera.ec.data.entities.Product
import com.kevinolivera.ec.presenter.ProductsPresenter
import com.kevinolivera.ec.view.ProductsView
import javax.inject.Inject

class ProductsActivity : AppCompatActivity(), ProductsView {

    @BindView(R.id.rvProducts)
    lateinit var rvProducts: RecyclerView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    lateinit var productsAdapter: RecyclerAdapter<ProductHolder, Product>

    @Inject
    lateinit var productPresenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        ButterKnife.bind(this)
        (application as App).injector.inject(this)

        initialize()
    }

    fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.activity_products_product_list)

        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        productsAdapter = RecyclerAdapter(this, ProductHolder.LAYOUT, ProductHolder::class.java)
        rvProducts.adapter = productsAdapter

        productsAdapter.setOnEventListener(object : RecyclerAdapter.OnEventListener<Product> {
            override fun onSelect(product: Product) {
                var intent = Intent(this@ProductsActivity, ProductActivity::class.java)
                intent.putExtra(ProductActivity.PRODUCT_ID, product.id)
                startActivity(intent)
            }
        })

        productPresenter.productsView = this
        productPresenter.getProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onProducts(products: MutableList<Product>) {
        productsAdapter.setList(products)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_view_cart -> {
                startActivity(Intent(this@ProductsActivity, CartActivity::class.java))
                return true
            }
        }
        return true
    }

    override fun onError(message: String) {
        Log.e(ProductsActivity::class.simpleName, message)
    }

}
