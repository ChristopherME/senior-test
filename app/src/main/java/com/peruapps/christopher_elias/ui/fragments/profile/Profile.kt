package com.peruapps.christopher_elias.ui.fragments.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.peruapps.christopher_elias.BR
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.databinding.FragmentProfileBinding
import com.peruapps.christopher_elias.room.data.PlaceRoom
import com.peruapps.christopher_elias.ui.base.BaseFragment
import java.util.ArrayList

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class Profile : BaseFragment<FragmentProfileBinding, ProfileViewModel>(), ProfileNavigator{

    private lateinit var viewModel: ProfileViewModel

    //region Base
    override fun getLayoutId() = R.layout.fragment_profile

    override fun getBindingVariable() = BR.profileViewModel

    override fun getViewModel(): ProfileViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
        return viewModel
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.places.observe(this, Observer<ArrayList<PlaceRoom>>{
            viewModel.addPlacesToList(it)
        })
    }

    override fun dismissView() {
        activity?.onBackPressed()
    }
    override fun showLoading(isLoading: Boolean) {
        handleProgress(isLoading)
    }

    override fun showMessage(message: String?) {
        showToast(message)
    }

    override fun showMessageFromResource(stringId: Int) {
        showToast(stringId)
    }
    //endregion

}