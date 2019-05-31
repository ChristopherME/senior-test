package com.peruapps.christopher_elias.utils.bindingtools

import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */


@BindingAdapter("setImageControllerResize","setResizeWidth","setResizeHeight")
fun setImageControllerResize(photo: SimpleDraweeView, uri: String, width: Int, height: Int){
    if (uri.isNotEmpty()){
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
            .setResizeOptions(ResizeOptions(width, height))
            .build()
        photo.controller = Fresco.newDraweeControllerBuilder()
            .setOldController(photo.controller)
            .setImageRequest(request)
            .build()
    } else {
        photo.visibility = View.GONE
    }
}