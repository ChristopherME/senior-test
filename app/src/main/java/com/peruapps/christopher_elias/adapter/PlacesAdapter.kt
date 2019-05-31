package com.peruapps.christopher_elias.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.peruapps.christopher_elias.BR
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.entity.Place
import com.peruapps.christopher_elias.ui.holder.MasterViewHolder

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class PlacesAdapter(var items: MutableList<Place>,
                    var listener: (Place) -> Unit): RecyclerView.Adapter<MasterViewHolder<*>>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder<*> {
        val binding: ViewDataBinding? = DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false))
        return MasterViewHolder(binding!!)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MasterViewHolder<*>, position: Int) {
        val model = items[position]
        holder.binding.setVariable(BR.model, model)
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener { listener.invoke(model) }
    }

    fun bindNewItems(data: MutableList<Place>) {
        this.items.clear()
        this.items.addAll(data)
        this.notifyDataSetChanged()
    }
}