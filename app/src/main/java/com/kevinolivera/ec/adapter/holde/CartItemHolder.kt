package com.kevinolivera.ec.adapter.holde

import android.content.Context
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.kevinolivera.ec.R
import com.kevinolivera.ec.adapter.RecyclerAdapter
import com.kevinolivera.ec.data.entities.CartItem

class CartItemHolder(var view: View, var context: Context) :RecyclerHolder<CartItem>(view) {

    @BindView(R.id.tvName)
    lateinit var tvName         : TextView

    @BindView(R.id.tvDescription)
    lateinit var tvDescription  : TextView

    @BindView(R.id.tvPrice)
    lateinit var tvPrice        : TextView

    @BindView(R.id.tvTotal)
    lateinit var tvTotal        : TextView

    override fun display(m: CartItem, eventListener: RecyclerAdapter.OnEventListener<CartItem>?) {
        ButterKnife.bind(this, view)
        tvName.text = "${m.name} (${m.count})"
        tvDescription.text = m.description
        tvPrice.text = "₱ ${m.price}"
        tvTotal.text = "₱ ${m.price!! * m.count}"
        view.setOnClickListener {
            eventListener?.onSelect(m)
        }

    }

    companion object {
        const val LAYOUT = R.layout.item_cart
    }

}