package com.kevinolivera.ec.adapter.holde

import android.content.Context
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.kevinolivera.ec.R
import com.kevinolivera.ec.adapter.RecyclerAdapter
import com.kevinolivera.ec.data.entities.Product

class ProductHolder(var view: View, var context: Context) :RecyclerHolder<Product>(view) {

    @BindView(R.id.tvName)
    lateinit var tvName         : TextView

    @BindView(R.id.tvDescription)
    lateinit var tvDescription  : TextView

    @BindView(R.id.tvPrice)
    lateinit var tvPrice        : TextView

    override fun display(m: Product, eventListener: RecyclerAdapter.OnEventListener<Product>?) {
        ButterKnife.bind(this, view)
        tvName.text = m.name
        tvDescription.text = m.description
        tvPrice.text = "₱ ${m.price}"
        view.setOnClickListener {
            eventListener?.onSelect(m)
        }
    }

    companion object {
        const val LAYOUT = R.layout.item_product
    }

}