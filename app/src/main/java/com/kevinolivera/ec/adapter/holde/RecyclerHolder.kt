package com.kevinolivera.ec.adapter.holde

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kevinolivera.ec.adapter.RecyclerAdapter

abstract class RecyclerHolder<Entity>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun display(m : Entity, eventListener: RecyclerAdapter.OnEventListener<Entity>?)

    companion object {
        inline fun <Holder> newInstance(itemView: View, clazz: Class<Holder>, activity: Activity) : RecyclerView.ViewHolder =
            when(clazz) {
                ProductHolder::class.java -> ProductHolder(itemView, activity)
                CartItemHolder::class.java -> CartItemHolder(itemView, activity)
            else -> throw IllegalArgumentException()
        }
    }
}
