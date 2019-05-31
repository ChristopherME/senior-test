package com.peruapps.christopher_elias.ui.fragments.login

import androidx.annotation.StringRes
import com.peruapps.christopher_elias.ui.base.BaseNavigator

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface LogInNavigator : BaseNavigator {

    fun showLoadingDialog(isLoading: Boolean, @StringRes resId: Int? = null)

    fun showHomeView()

}