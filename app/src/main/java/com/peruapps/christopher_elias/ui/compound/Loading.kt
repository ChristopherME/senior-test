package com.peruapps.christopher_elias.ui.compound

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.StringRes
import com.peruapps.christopher_elias.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class Loading(context: Context, @StringRes private val resourceString: Int? = null) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_loading)

        if (resourceString != null){
            changeMessage(resourceString)
        }

        setCancelable(false)
    }

    private fun changeMessage(resourceString: Int) {
        dialog_loading_message.text = context.resources.getString(resourceString)
    }
}