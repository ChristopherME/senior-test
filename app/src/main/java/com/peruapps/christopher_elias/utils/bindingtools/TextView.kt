package com.peruapps.christopher_elias.utils.bindingtools

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.peruapps.christopher_elias.R

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@BindingAdapter("setCreatedAt")
fun setPlaceCreatedAt(textView: TextView, createdAt: String){
    textView.text = textView.resources.getString(R.string.text_saved_on, createdAt)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setLat")
fun setPlaceLat(textView: TextView, lat: Double){
    textView.text = "Lat: $lat"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setLong")
fun setPlaceLong(textView: TextView, long: Double){
    textView.text = "Long: $long"
}