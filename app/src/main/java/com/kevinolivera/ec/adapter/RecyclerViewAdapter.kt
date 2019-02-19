package com.kevinolivera.ec.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevinolivera.ec.adapter.holde.RecyclerHolder

class RecyclerAdapter<Holder: RecyclerHolder<Entity>, Entity> (
    var activity: Activity,
    var layout:Int,
    var holderClass:Class<Holder>): RecyclerView.Adapter<Holder>()  {

    private val list : MutableList<Entity>  = ArrayList()
    private var eventListener: OnEventListener<Entity>? = null

    fun addList(list:MutableList<Entity>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setList(list:MutableList<Entity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.list.clear()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setOnEventListener(eventListener: OnEventListener<Entity>?) {
        this.eventListener = eventListener
    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int) : Holder {
        return RecyclerHolder.newInstance<Holder>(LayoutInflater.from(activity.baseContext).inflate(layout, parent, false), holderClass, activity) as Holder
    }

    override fun onBindViewHolder(holder:Holder, position:Int) {
        holder.display(list[position], eventListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnEventListener<Entity> {
        fun onSelect(model: Entity)
    }
}
