package com.peruapps.christopher_elias.ui.fragments.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.peruapps.christopher_elias.BR
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.databinding.FragmentLogInBinding
import com.peruapps.christopher_elias.ui.base.BaseFragment
import com.peruapps.christopher_elias.ui.fragments.home.Home

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class LogIn : BaseFragment<FragmentLogInBinding, LogInViewModel>(), LogInNavigator {

    private lateinit var viewModel: LogInViewModel

    //region Base
    override fun getLayoutId() = R.layout.fragment_log_in

    override fun getBindingVariable() = BR.logInViewModel

    override fun getViewModel(): LogInViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LogInViewModel::class.java)
        return viewModel
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun showLoadingDialog(isLoading: Boolean, resId: Int?) {
        handleProgress(isLoading, resId)
    }

    override fun showHomeView() {
        replaceFragment(Home(), R.id.containerFrameLayout, false)
    }

    override fun showMessage(message: String?) {
        showToast(message)
    }

    override fun showMessageFromResource(stringId: Int) {
        showToast(stringId)
    }
}