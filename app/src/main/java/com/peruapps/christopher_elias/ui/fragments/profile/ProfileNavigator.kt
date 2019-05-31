package com.peruapps.christopher_elias.ui.fragments.profile

import com.peruapps.christopher_elias.ui.base.BaseNavigator

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface ProfileNavigator : BaseNavigator {
    fun dismissView()
    fun showLoading(isLoading: Boolean)
}