package com.peruapps.christopher_elias.utils.bindingtools

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peruapps.christopher_elias.adapter.PlacesAdapter
import com.peruapps.christopher_elias.adapter.PlacesRoomAdapter

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@BindingAdapter("placesAdapter")
fun <T> setPlacesAdapter(recyclerView: RecyclerView, masterAdapter: PlacesAdapter) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = masterAdapter
}


@BindingAdapter("savedPlacesAdapter")
fun <T> setPlacesRoomAdapter(recyclerView: RecyclerView, masterAdapter: PlacesRoomAdapter) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = masterAdapter
}