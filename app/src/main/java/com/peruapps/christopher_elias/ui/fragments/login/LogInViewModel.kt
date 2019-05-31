package com.peruapps.christopher_elias.ui.fragments.login

import androidx.databinding.ObservableField
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.data.DataManager
import com.peruapps.christopher_elias.extensions.isEmail
import com.peruapps.christopher_elias.extensions.value
import com.peruapps.christopher_elias.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

// Basic fireBase auth with email and password.
class LogInViewModel @Inject constructor(private val dataManager: DataManager) : BaseViewModel<LogInNavigator>() {

    //TODO: Delete default values
    var email = ObservableField<String>("Agrega tu Correo")
    var pwd = ObservableField<String>("")

    fun logIn() {
        if (!formValid())
            return

        getNavigator().showLoadingDialog(true)
        dataManager.getFireBaseAuth().signInWithEmailAndPassword(email.value(), pwd.value())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //TODO("Save user locally too")
                    getNavigator().showHomeView()
                } else {
                    logE(task.exception?.message)
                    getNavigator().showMessageFromResource(R.string.error_general)
                }
                getNavigator().showLoadingDialog(false)
            }
    }

    fun signIn() {
        if (!formValid())
            return

        getNavigator().showLoadingDialog(true)
        dataManager.getFireBaseAuth().createUserWithEmailAndPassword(email.value(), pwd.value())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //TODO("Save user locally too")
                    getNavigator().showHomeView()
                } else {
                    logE(task.exception?.message)
                    getNavigator().showMessageFromResource(R.string.error_general)
                }
                getNavigator().showLoadingDialog(false)
            }
    }

    private fun formValid() : Boolean {

        if (email.value().isEmpty()){
            //show some message
            return false
        }

        if (!email.value().isEmail()){
            //show some message
            return false
        }

        if (pwd.value().isEmpty()){
            //show some message
            return false
        }

        // Firebase auth requires at least 6 characters for passwords.
        if (pwd.value().length < 6){
            //show some message
            return false
        }

        return true
    }
}