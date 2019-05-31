package com.peruapps.christopher_elias.ui.activities.main

import android.os.Handler
import com.peruapps.christopher_elias.data.DataManager
import com.peruapps.christopher_elias.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class MainViewModel @Inject constructor(private val dataManager: DataManager): BaseViewModel<MainNavigator>() {

    init {
        checkForCurrentUser()
    }

    private fun checkForCurrentUser() {
        //Wait half sec till the view gets created.
        Handler().postDelayed({
            if (dataManager.getFireBaseAuth().currentUser == null) {
                getNavigator().showLoginView()
            } else {
                getNavigator().showHomeView()
            }
        },500)
    }
}