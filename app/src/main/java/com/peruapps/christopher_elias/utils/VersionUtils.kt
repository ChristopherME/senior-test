package com.peruapps.christopher_elias.utils

import android.os.Build

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

object VersionUtils {

    fun isNougat() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    fun isOreo() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    fun isMarshmallow() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}