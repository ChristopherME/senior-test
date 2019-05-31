package com.peruapps.christopher_elias.ui.base

import androidx.annotation.StringRes

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface BaseNavigator {

    fun showMessage(message: String?)

    fun showMessageFromResource(@StringRes stringId: Int)
}