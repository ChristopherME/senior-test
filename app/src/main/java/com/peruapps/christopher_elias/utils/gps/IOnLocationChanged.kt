package com.peruapps.christopher_elias.utils.gps

import android.location.Location

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface IOnLocationChanged {
    fun LocationChanged(location: Location)
    fun LocationNeedPermissions()
}