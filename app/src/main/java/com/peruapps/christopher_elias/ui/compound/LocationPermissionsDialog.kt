package com.peruapps.christopher_elias.ui.compound

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AlertDialog
import com.peruapps.christopher_elias.R
import kotlinx.android.synthetic.main.dialog_start_saving_places.*

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */


class LocationPermissionsDialog(context: Context, val callback: (Boolean, LocationPermissionsDialog) -> Unit) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_start_saving_places)

        val animation = ScaleAnimation(0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)

        dialogWarning.animation = animation
        animation.duration = 300
        animation.start()

        buttonAccept.setOnClickListener {  callback( true, this@LocationPermissionsDialog) }
        buttonDecline.setOnClickListener { callback( false, this@LocationPermissionsDialog)  }
    }
}
