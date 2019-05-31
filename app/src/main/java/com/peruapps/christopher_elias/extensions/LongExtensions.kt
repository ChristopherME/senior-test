package com.peruapps.christopher_elias.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

fun Long.toDate() : String{

    val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
    val resultD = Date(this)

    return sdf.format(resultD)
}